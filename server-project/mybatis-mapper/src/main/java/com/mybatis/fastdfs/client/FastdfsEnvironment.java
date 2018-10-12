package com.mybatis.fastdfs.client;

public class FastdfsEnvironment {

    /**
     * 文件服务器地址
     */
    private String fileServerAddr;

    /**
     * FastDFS秘钥
     */
    private String fastDFSHttpSecretKey;

    /**
     * TrackerServer 配置文件路径
     */
    private String fastdfsConfigPath;

    /**
     * 最大连接数 default 8.
     */
    private int maxStorageConnection;

    /**
     * 文件最大的大小
     */
    private Integer maxFileSize;

    /**
     * 文件名称Key
     */
    private String fileName;

    public String getFileServerAddr() {
        return fileServerAddr;
    }

    public void setFileServerAddr(String fileServerAddr) {
        this.fileServerAddr = fileServerAddr;
    }

    public String getFastDFSHttpSecretKey() {
        return fastDFSHttpSecretKey;
    }

    public void setFastDFSHttpSecretKey(String fastDFSHttpSecretKey) {
        this.fastDFSHttpSecretKey = fastDFSHttpSecretKey;
    }

    public String getFastdfsConfigPath() {
        return fastdfsConfigPath;
    }

    public void setFastdfsConfigPath(String fastdfsConfigPath) {
        this.fastdfsConfigPath = fastdfsConfigPath;
    }

    public int getMaxStorageConnection() {
        return maxStorageConnection;
    }

    public void setMaxStorageConnection(int maxStorageConnection) {
        this.maxStorageConnection = maxStorageConnection;
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
