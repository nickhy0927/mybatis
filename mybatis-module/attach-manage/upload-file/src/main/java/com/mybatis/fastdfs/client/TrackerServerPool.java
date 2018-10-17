package com.mybatis.fastdfs.client;

import java.io.IOException;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.mybatis.common.utils.SpringContextHolder;

/**
 * TrackerServer 对象池
 * <p>
 *
 * @version 1.0
 * @date 2017-10-14 15:23
 */
public class TrackerServerPool {
    /**
     * org.slf4j.Logger
     */
    private static Logger logger = LoggerFactory.getLogger(TrackerServerPool.class);

    /**
     * 最大连接数 default 8.
     */
    @Value("${max_storage_connection}")
    private static int maxStorageConnection;

    /**
     * TrackerServer 对象池.
     * GenericObjectPool 没有无参构造
     */
    private static GenericObjectPool<TrackerServer> trackerServerPool;

    private TrackerServerPool() {
    }

    private static synchronized GenericObjectPool<TrackerServer> getObjectPool() {
        if (trackerServerPool == null) {
            try {
                // 加载配置文件
                FastdfsEnvironment fastdfsEnvironment = SpringContextHolder.getBean(FastdfsEnvironment.class);
                ClientGlobal.initByProperties(fastdfsEnvironment.getFastdfsConfigPath());
            } catch (IOException | MyException e) {
                e.printStackTrace();
            }

            if (logger.isDebugEnabled()) {
                logger.debug("ClientGlobal configInfo: {}", ClientGlobal.configInfo());
            }

            // Pool配置
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMinIdle(2);
            if (maxStorageConnection > 0) {
                poolConfig.setMaxTotal(maxStorageConnection);
            }

            trackerServerPool = new GenericObjectPool<>(new TrackerServerFactory(), poolConfig);
        }
        return trackerServerPool;
    }

    /**
     * 获取 TrackerServer
     *
     * @return TrackerServer
     * @throws FastDFSException
     */
    public static TrackerServer borrowObject() throws FastDFSException {
        TrackerServer trackerServer = null;
        try {
            trackerServer = getObjectPool().borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof FastDFSException) {
                throw (FastDFSException) e;
            }
        }
        return trackerServer;
    }

    /**
     * 回收 TrackerServer
     *
     * @param trackerServer 需要回收的 TrackerServer
     */
    public static void returnObject(TrackerServer trackerServer) {

        getObjectPool().returnObject(trackerServer);
    }


}
