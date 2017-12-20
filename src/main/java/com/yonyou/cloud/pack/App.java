/**
 * 
 */
package com.yonyou.cloud.pack;

/**
 * @author zhukai
 *
 */
public class App {
	public static void main(String[] args) {
		Berg mov = new Berg();
		mov.setClassPath(
				"/etc/bigjar/source/middleware/yyeye-public-3.0.1-SNAPSHOT.jar;/etc/bigjar/source/middleware/sentinel-public-3.0.1-SNAPSHOT.jar");
		mov.setPrjHome("/etc/bigjar/target");
		mov.execute();
	}
}
