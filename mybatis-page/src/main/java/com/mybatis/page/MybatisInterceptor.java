package com.mybatis.page;

import com.alibaba.fastjson.JSON;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.page.dialect.Dialect;
import com.mybatis.page.entity.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * mybatis 拦截器扩展
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class MybatisInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);
    protected static int MAPPED_STATEMENT_INDEX = 0;
    protected static int PARAMETER_INDEX = 2;
    protected static int REBOUNDS_INDEX = 2;
    protected Dialect dialect;
    private String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Object intercept(Invocation invocation) throws Throwable {
        processMybatisIntercept(invocation);
        return invocation.proceed();
    }

    private void processMybatisIntercept(Invocation invocation) {
        Object[] queryArgs = invocation.getArgs();
        MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        MappedStatement mappedStatement = (MappedStatement) queryArgs[0];
        String statementId = mappedStatement.getId();
        boolean isMatch = Pattern.matches(pattern, statementId);
        if (!isMatch) {
            logger.debug("普通的SQL查询-->", statementId);
            return;
        }
        Object parameter = queryArgs[PARAMETER_INDEX];
        if (parameter == null) {
            logger.debug("普通的SQL查询");
            return;
        }
        Page<?> page = new Page<>();
        PageSupport support = convertParameter(parameter);
        if (dialect.supportsLimitOffset() && support != null) {
            logger.debug("分页查询==>>");
            BoundSql boundSql = ms.getBoundSql(parameter);
            String sql = boundSql.getSql().trim();
            int offset = support.getCurrentPage();
            int limit = support.getpageSize();
            CachingExecutor executor = (CachingExecutor) invocation.getTarget();
            Transaction transaction = executor.getTransaction();
            try {
                Connection connection = transaction.getConnection();
                /**
                 * 查询总记录数
                 */
                this.setTotalRecord(page, ms, connection, parameter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit);
                offset = RowBounds.NO_ROW_OFFSET;
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }
            limit = RowBounds.NO_ROW_LIMIT;
            queryArgs[REBOUNDS_INDEX] = new RowBounds(offset, limit);
            BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
            MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
        } else {
            logger.debug("普通的SQL查询");
        }

    }

    private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection, Object parameterObject) {
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        String sql = boundSql.getSql();
        String countSql = removeBreakingWhitespace(dialect.getCountSql(sql));
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, parameterObject);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, countBoundSql);
        PreparedStatement statement = null;
        ResultSet rs = null;
        parameterHandler.getParameterObject();
        logger.debug("Preparing: {} ", countSql.toString());
        logger.debug("Parameters: {} ", JSON.toJSONString(parameterObject));
        try {
            statement = connection.prepareStatement(countSql);
            parameterHandler.setParameters(statement);
            rs = statement.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                logger.debug("Total :{}", totalRecord);
                page.setTotalCount(totalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String removeBreakingWhitespace(String countSql) {
        StringTokenizer whitespaceStripper = new StringTokenizer(countSql);
        StringBuilder builder = new StringBuilder();
        while (whitespaceStripper.hasMoreTokens()) {
            builder.append(whitespaceStripper.nextToken());
            builder.append(" ");
        }
        return builder.toString();
    }

    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        // builder.keyProperty(ms.getKeyProperty());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        MappedStatement newMs = builder.build();
        return newMs;
    }

    private PageSupport convertParameter(Object parameter) {
        if (parameter instanceof PageSupport)
            return (PageSupport) parameter;
        if (parameter instanceof Map<?, ?>) {
            try {
                PageSupport support = new PageSupport();
                BeanUtils.copyProperties(support, parameter);
                return support;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return new PageSupport();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }

    /**
     * @param dialectClass the dialectClass to set
     */
    public void setDialectClass(String dialectClass) {
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
        }
        logger.debug(this.getClass().getSimpleName() + ".dialect=[{}]", dialect.getClass().getSimpleName());
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
