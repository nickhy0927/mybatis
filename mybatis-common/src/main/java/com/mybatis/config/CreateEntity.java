package com.mybatis.config;

import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateEntity {
    public static void main(String[] args) {
        test("createEntity.ftl");
    }

    public static void test(String ftlFleName) {
        try {
            Map<String, Object> root = new HashMap<>();
            root.put("str", "hello world!");
            root.put("title", "自动生成文件");
            root.put("username", "yuanhuangd");
            List<String> data = Lists.newArrayList();
            data.add("11");
            data.add("12");
            data.add("13");
            root.put("data", data);
            String path = CreateEntity.getFreemarkerPath("pages");
//            String freemarkerFile = getFreemarkerFile(path,"test.ftl");
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(path));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template temp = cfg.getTemplate(ftlFleName);
            System.out.println(temp.getName());
            String fileName = "demo.html";
            String filePath = "D:/web/template/";
            File pathFile = new File(filePath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            File file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            temp.process(root, bw);
            bw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static String getFreemarkerFile(String path, String fileName) {
        String file = "/META-INF/resources/" + path + "/" + fileName;
        URL fileURL = CreateEntity.class.getClass().getResource(file);
        if (fileURL != null) {
            String urlFile = fileURL.getFile();
            System.out.println(urlFile);
            return urlFile;
        }
        return null;
    }

    protected static String getFreemarkerPath(String path) {
        String file = "/META-INF/resources/" + path + "/";
        URL fileURL = CreateEntity.class.getClass().getResource(file);
        if (fileURL != null) {
            String urlFile = fileURL.getFile();
            System.out.println(urlFile);
            return urlFile;
        }
        return null;
    }
}
