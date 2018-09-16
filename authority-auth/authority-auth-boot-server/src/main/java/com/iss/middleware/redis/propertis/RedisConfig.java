package com.iss.middleware.redis.propertis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:middleware/redis/redis.properties")
@Component
public class RedisConfig extends CachingConfigurerSupport {

    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * redis服务器ip
     */
    @Value("${redis.host}")
    private String host;

    /**
     * redis服务端口
     */
    @Value("${redis.port}")
    private Integer port;

    /**
     * redis数据库密码
     */
    @Value("${redis.password}")
    private String password;

    /**
     * redis数据库从几开始
     */
    @Value("${redis.dbIndex}")
    private Integer dbIndex;

    /**
     * 有效时间
     */
    @Value("${redis.expiration}")
    private Integer expiration;

    /**
     * 最大活跃数
     */
    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    /**
     * 连接等待时间
     */
    @Value("${redis.maxWait}")
    private Integer maxWait;

    @Value("${redis.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${redis.timeout}")
    private Integer timeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(Integer dbIndex) {
        this.dbIndex = dbIndex;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setTestOnCreate(true);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }
}
