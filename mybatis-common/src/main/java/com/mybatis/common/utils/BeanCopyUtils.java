package com.mybatis.common.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanCopyUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(BeanCopyUtils.class);

    static {
        ConvertUtils.register(new SqlDateConverter(null), Date.class);
        ConvertUtils.register(new SqlDateConverter(null), java.util.Date.class);
        ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
        ConvertUtils.register(new BooleanConverter(null), Boolean.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        ConvertUtils.register(new StringConverter(null), String.class);
    }


    /**
     * 拷贝对象
     *
     * @param origObj 源对象
     * @return desObj 目标对象
     */
    @SuppressWarnings("unchecked")
	public static <T, E> T copyObject(Class<T> t, E origObj) {
        try {
            Object desObj = t.newInstance();
            if (origObj != null && desObj != null)
                BeanUtils.copyProperties(desObj, origObj);
            return (T) desObj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 拷贝List对象到另一个list对象
     *
     * @param desClass   源List对象
     * @param sourceList 目标List对象
     * @return List
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> List<T> copyListObjToListObj(Class<T> desClass, List sourceList) {
        List desList = new ArrayList();
        if (sourceList != null) {
            for (int i = 0; i < sourceList.size(); i++) {
                try {
                    Object sourceObj = sourceList.get(i);
                    Object desObj = desClass.newInstance();
                    BeanUtils.copyProperties(desObj, sourceObj);
                    desList.add(desObj);
                } catch (Exception e) {
                    LOGGER.error("list copy error", e);
                    throw new RuntimeException("list copy error", e);
                }

            }
        }
        return desList;
    }
}
