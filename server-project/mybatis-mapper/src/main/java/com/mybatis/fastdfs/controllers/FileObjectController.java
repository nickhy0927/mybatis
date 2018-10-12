package com.mybatis.fastdfs.controllers;

import com.mybatis.fastdfs.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件接口
 * <p>
 *
 * @author jiangzhou.bo@hand-china.com
 * @version 1.0
 * @name FileObjectController
 * @date 2017-10-15 14:09
 */
@Controller
@RequestMapping("/fastdfs")
public class FileObjectController {

    @Autowired
    private FastdfsClient fastdfsClient;

    @Autowired
    private FastdfsEnvironment fastdfsEnvironment;

    @RequestMapping("/test")
    @ResponseBody
    public FileResponseData test() {
        return new FileResponseData(true);
    }

    /**
     * 上传文件通用，只上传文件到服务器，不会保存记录到数据库
     *
     * @param file
     * @return 返回文件路径等信息
     */
    @RequestMapping(value = "/upload/file/sample")
    @ResponseBody
    public FileResponseData uploadFileSample(MultipartFile file) {
        return uploadSample(file);
    }

    /**
     * 只能上传图片，只上传文件到服务器，不会保存记录到数据库. <br>
     * 会检查文件格式是否正确，默认只能上传 ['png', 'gif', 'jpeg', 'jpg'] 几种类型.
     *
     * @param file
     * @return 返回文件路径等信息
     */
    @RequestMapping("/upload/image/sample")
    @ResponseBody
    public FileResponseData uploadImageSample(@RequestParam MultipartFile file) {
        // 检查文件类型
        if (!FileCheck.checkImage(file.getOriginalFilename())) {
            FileResponseData responseData = new FileResponseData(false);
            responseData.setCode(ErrorCode.FILE_TYPE_ERROR_IMAGE.CODE);
            responseData.setMessage(ErrorCode.FILE_TYPE_ERROR_IMAGE.MESSAGE);
            return responseData;
        }

        return uploadSample(file);
    }

    /**
     * 只能上传文档，只上传文件到服务器，不会保存记录到数据库. <br>
     * 会检查文件格式是否正确，默认只能上传 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc', 'docx'] 几种类型.
     *
     * @param file
     * @return 返回文件路径等信息
     */
    @RequestMapping("/upload/doc/sample")
    @ResponseBody
    public FileResponseData uploadDocSample(@RequestParam MultipartFile file) {
        // 检查文件类型
        if (!FileCheck.checkDoc(file.getOriginalFilename())) {
            FileResponseData responseData = new FileResponseData(false);
            responseData.setCode(ErrorCode.FILE_TYPE_ERROR_DOC.CODE);
            responseData.setMessage(ErrorCode.FILE_TYPE_ERROR_DOC.MESSAGE);
            return responseData;
        }

        return uploadSample(file);
    }

    /**
     * 以附件形式下载文件
     *
     * @param filePath 文件地址
     * @param response
     */
    @RequestMapping("/download/file")
    public void downloadFile(String filePath, HttpServletResponse response) throws FastDFSException {
        try {
            fastdfsClient.downloadFile(filePath, response);
        } catch (FastDFSException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 获取图片 使用输出流输出字节码，可以使用< img>标签显示图片<br>
     *
     * @param filePath 图片地址
     * @param response
     */
    @RequestMapping("/download/image")
    public void downloadImage(String filePath, HttpServletResponse response) throws FastDFSException {
        try {
            fastdfsClient.downloadFile(filePath, response.getOutputStream());
        } catch (FastDFSException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据指定的路径删除服务器文件，适用于没有保存数据库记录的文件
     *
     * @param filePath
     */
    @RequestMapping("/delete/file")
    public FileResponseData deleteFile(String filePath) {
        FileResponseData responseData = new FileResponseData();
        try {
            fastdfsClient.deleteFile(filePath);
        } catch (FastDFSException e) {
            e.printStackTrace();
            responseData.setSuccess(false);
            responseData.setCode(e.getCode());
            responseData.setMessage(e.getMessage());
        }
        return responseData;
    }

    /**
     * 获取访问文件的token
     *
     * @param filePath 文件路径
     * @return
     */
    @RequestMapping("/get/token")
    @ResponseBody
    public FileResponseData getToken(String filePath) {
        FileResponseData responseData = new FileResponseData();
        // 设置访文件的Http地址. 有时效性.
        String token = fastdfsClient.getToken(filePath, fastdfsEnvironment.getFastDFSHttpSecretKey());
        responseData.setToken(token);
        responseData.setHttpUrl(fastdfsEnvironment.getFileServerAddr() + "/" + filePath + "?" + token);
        return responseData;
    }

    /**
     * 上传通用方法，只上传到服务器，不保存记录到数据库
     *
     * @param file
     * @return
     */
    private FileResponseData uploadSample(MultipartFile file) {
        FileResponseData responseData = new FileResponseData();
        try {
            // 上传到服务器
            String filepath = fastdfsClient.uploadFileWithMultipart(file);
            responseData.setFileName(file.getOriginalFilename());
            responseData.setFilePath(filepath);
            responseData.setFileType(FastdfsClient.getFilenameSuffix(file.getOriginalFilename()));
            // 设置访文件的Http地址. 有时效性.
            String token = fastdfsClient.getToken(filepath, fastdfsEnvironment.getFastDFSHttpSecretKey());
            responseData.setToken(token);
            responseData.setHttpUrl(fastdfsEnvironment.getFileServerAddr() + "/" + filepath + "?" + token);
        } catch (FastDFSException e) {
            responseData.setSuccess(false);
            responseData.setCode(e.getCode());
            responseData.setMessage(e.getMessage());
        }

        return responseData;
    }

}
