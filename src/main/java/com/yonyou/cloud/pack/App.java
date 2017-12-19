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
				"/Users/li/Documents/workspace3lll/cobin/target/classes:/Users/li/.m2/repository/org/springframework/spring-context/5.0.1.BUILD-SNAPSHOT/spring-context-5.0.1.BUILD-SNAPSHOT.jar:/Users/li/.m2/repository/org/springframework/spring-aop/5.0.1.BUILD-SNAPSHOT/spring-aop-5.0.1.BUILD-SNAPSHOT.jar:/Users/li/.m2/repository/org/springframework/spring-beans/5.0.1.BUILD-SNAPSHOT/spring-beans-5.0.1.BUILD-SNAPSHOT.jar:/Users/li/.m2/repository/org/springframework/spring-core/5.0.1.BUILD-SNAPSHOT/spring-core-5.0.1.BUILD-SNAPSHOT.jar:/Users/li/.m2/repository/org/springframework/spring-jcl/5.0.1.BUILD-SNAPSHOT/spring-jcl-5.0.1.BUILD-SNAPSHOT.jar:/Users/li/.m2/repository/org/springframework/spring-expression/5.0.1.BUILD-SNAPSHOT/spring-expression-5.0.1.BUILD-SNAPSHOT.jar:/Users/li/.m2/repository/com/yonyou/cloud/middleware/iceberg-isolater-public/3.0.1-SNAPSHOT/iceberg-isolater-public-3.0.1-SNAPSHOT.jar");
		mov.setPrjHome("/Users/li/Documents/workspace3lll/cobin");
		mov.execute();
	}
}
