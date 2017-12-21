package com.yonyou.cloud.pack;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;

public class Berg extends Task {
	
	private static final String SPI_SUFFIX = File.separator+"META-INF"+File.separator+"services";
	
	private  static final String lineSeparator=java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));
	
	private String home;
	private String cls;
	private final String windowstr = "Windows";
	static final int buffer = 1024;

	public void setClassPath(String paramString) {
		System.out.println("ClassPath: " + paramString);
		this.cls = paramString;
	}

	public void setPrjHome(String paramString) {
		System.out.println("PrjHome: " + paramString);
		this.home = paramString;
	}

	public void execute() {
		String str1 = ":";
		String str2 = System.getProperty("os.name");
		if (str2.toLowerCase().contains("Windows".toLowerCase())) {
			str1 = ";";
		}
		int total = 0;
		for (String str3 : this.cls.split(str1)) {
			total+=1;
			if (str3.endsWith(".jar")) {
				if ((str3.indexOf("-public") != -1) && (str3.indexOf("middleware") != -1)) {
					System.out.println("unzip" + str3);
					unzip(str3, this.home + "/target/classes/");
				} else {
					File localFile1 = new File(str3);
					File localFile2 = new File(this.home + "/target/classes/BOOT-INF/lib/" + localFile1.getName());
					System.out
							.println("copy " + localFile1.getAbsolutePath() + " to : " + localFile2.getAbsolutePath());
					try {
						FileUtils.getFileUtils().copyFile(localFile1, localFile2);
					} catch (IOException localIOException) {
						localIOException.printStackTrace();
					}
				}
			}
		}
	}
	
	public boolean isSpiFile(String filepath) {
		String filedir = filepath.substring(0, filepath.lastIndexOf(File.separator));
		if(filedir.toLowerCase().endsWith(SPI_SUFFIX.toLowerCase())) {
			return true;
		}else {
			return false;
		}
		
	}

	public void unzip(String sourcePath, String targetPath) {
		int i = -1;

		File localFile1 = null;
		InputStream localInputStream = null;
		FileOutputStream localFileOutputStream = null;
		BufferedOutputStream localBufferedOutputStream = null;

		File localFile2 = new File(targetPath);
		if (!localFile2.exists()) {
			localFile2.mkdir();
		}
		ZipFile localZipFile = null;
		try {
			localZipFile = new ZipFile(sourcePath);
			Enumeration localEnumeration = localZipFile.entries();
			while (localEnumeration.hasMoreElements()) {
				byte[] arrayOfByte = new byte[1024];

				ZipEntry localZipEntry = (ZipEntry) localEnumeration.nextElement();

				String str = localZipEntry.getName();
				int j = 0;
				if (str.lastIndexOf("/") != -1) {
					j = 1;
				}
				str = targetPath + str;
				if (localZipEntry.isDirectory()) {
					localFile1 = new File(str);
					if (!localFile1.exists()) {
						localFile1.mkdirs();
					}
				} else {
					localFile1 = new File(str);
					if ((!localFile1.exists()) && (j != 0)) {
						new File(str.substring(0, str.lastIndexOf("/"))).mkdirs();
					}
					

						
					localFile1.createNewFile();
					
					
					localInputStream = localZipFile.getInputStream(localZipEntry);
					//判断是否为java spi文件
					if(isSpiFile(localFile1.getPath())) {
						localFileOutputStream = new FileOutputStream(localFile1,true);
						localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream, 1024);		
						while ((i = localInputStream.read(arrayOfByte)) > -1) {
							localBufferedOutputStream.write(arrayOfByte, 0, i);
						}

						localBufferedOutputStream.write(lineSeparator.getBytes(), 0, lineSeparator.getBytes().length);;
					}else {
						localFileOutputStream = new FileOutputStream(localFile1);
						localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream, 1024);
						while ((i = localInputStream.read(arrayOfByte)) > -1) {
							localBufferedOutputStream.write(arrayOfByte, 0, i);
						}
					}
					

					localBufferedOutputStream.flush();
					localBufferedOutputStream.close();
					localFileOutputStream.close();

					localInputStream.close();
				}
			}
			localZipFile.close();
			return;
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
			try {
				if (localBufferedOutputStream != null) {
					localBufferedOutputStream.close();
				}
				if (localFileOutputStream != null) {
					localFileOutputStream.close();
				}
				if (localInputStream != null) {
					localInputStream.close();
				}
				if (localZipFile != null) {
					localZipFile.close();
				}
			} catch (Exception localException3) {
				localException3.printStackTrace();
			}
		} finally {
			try {
				if (localBufferedOutputStream != null) {
					localBufferedOutputStream.close();
				}
				if (localFileOutputStream != null) {
					localFileOutputStream.close();
				}
				if (localInputStream != null) {
					localInputStream.close();
				}
				if (localZipFile != null) {
					localZipFile.close();
				}
			} catch (Exception localException4) {
				localException4.printStackTrace();
			}
		}
	}
}
