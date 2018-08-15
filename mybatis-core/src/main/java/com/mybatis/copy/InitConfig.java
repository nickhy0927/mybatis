package com.mybatis.copy;

import org.apache.commons.lang3.StringUtils;

public class InitConfig {
    /**
     * 目标路径
     */
    private String targetDir;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 需要拷贝的jar文件名称
     */
    private String[] jars;

    /**
     * 1 jsp 2 freemarker
     */
    private Integer fileType;

    private String absoluteWebappPath;

    public String getAbsoluteWebappPath() {
        return absoluteWebappPath;
    }

    public void setAbsoluteWebappPath(String absoluteWebappPath) {
        this.absoluteWebappPath = absoluteWebappPath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public void setJars(String[] jars) {
        this.jars = jars;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String[] getJars() {
        return jars;
    }

    public void init () throws Exception {
        if (StringUtils.isEmpty(absoluteWebappPath)) {
            throw new Exception("field absolutePath is null", new Throwable());
        }
    }
}
