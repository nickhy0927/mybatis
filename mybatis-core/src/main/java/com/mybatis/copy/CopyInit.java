package com.mybatis.copy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import com.mybatis.core.orm.config.SpringContextHolder;

public class CopyInit {

	@Autowired
	private InitConfig initConfig;

	public void init() {
		ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
		Resource resource = applicationContext
				.getResource("/WEB-INF" + fileSeparator + initConfig.getTargetDir() + File.separator);
		try {
			File file = resource.getFile();
			String target = file.getPath();
			String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			path = path.substring(1, path.length());
			int endIndex = path.lastIndexOf("/");
			path = path.substring(0, endIndex);
			try {
				path = java.net.URLDecoder.decode(path, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			try {
				for (String jarName : initConfig.getJars()) {
					accessJarFile(jarName, path, target);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static final String fileSeparator = System.getProperty("file.separator");

	public void accessJarFile(String jarFileName, String fromDir, String toDir) throws Exception {
		JarFile myJarFile = new JarFile(fromDir + fileSeparator + jarFileName);
		Enumeration<JarEntry> myEnum = myJarFile.entries();
		String filePath = "META-INF";
		while (myEnum.hasMoreElements()) {
			JarEntry myJarEntry = myEnum.nextElement();
			if (myJarEntry.getName().contains(filePath) && !myJarEntry.getName().contains("template")) {
				String p = filePath;
				if (initConfig.getFileType() == 1) {
					p += "/jspdir";
				}
				if (initConfig.getFileType() == 1
						&& (myJarEntry.getName().contains(p) || myJarEntry.getName().contains(filePath + "\\jspdir"))) {
					copyFile(toDir, myJarFile, filePath, myJarEntry);
				} else if (initConfig.getFileType() == 2 && (myJarEntry.getName().contains(filePath + "/freemarker")
						|| myJarEntry.getName().contains(filePath + "\\freemarker"))) {
					copyFile(toDir, myJarFile, filePath, myJarEntry);
				}
			}
		}
		myJarFile.close();
	}

	private void copyFile(String toDir, JarFile myJarFile, String filePath, JarEntry myJarEntry) throws IOException {
		String path = toDir + fileSeparator + myJarEntry.getName();
		if (myJarEntry.getName().contains("parent")) {
			path = initConfig.getAbsoluteWebappPath() + fileSeparator + myJarEntry.getName();
			toDir = initConfig.getAbsoluteWebappPath();
		}
		if (initConfig.getFileType() == 1)
			path = path.replaceAll(filePath + "/jspdir", "");
		else
			path = path.replaceAll(filePath + "/freemarker", "");
		if (!new File(toDir).exists()) {
			new File(toDir).mkdirs();
		}
		InputStream is = myJarFile.getInputStream(myJarEntry);
		File file = new File(path);
		// String jarEntryName = myJarEntry.getName();
		boolean bool = file.getName().indexOf(".") > 0;
		if (!bool) {
			if (!file.exists()) {
				file.mkdirs();
			}
		} else if (file.getName().endsWith(initConfig.getSuffix()) || file.getName().endsWith(".html")) {
			FileOutputStream fos;
			try {

				fos = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int len;
				while ((len = is.read(b)) != -1) {
					fos.write(b, 0, len);
				}

				fos.close();
				is.close();
			} catch (Exception e) {
				if (!file.exists()) {
					file.mkdirs();
				}
			}
		}
	}
}
