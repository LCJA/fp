package com.fp.core.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {
	private static Properties config;
	
	static Logger logger = Logger.getLogger(Configuration.class);
	
	public static Properties getConfig(){
		try{
			if(null == config){
				//File configFile = new File("D://test/fivepeople/src/main/resources/certificate/conf.properties");
				String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
				File configFile = new File(path+"/certificate/conf.properties");
				if(configFile.exists() && configFile.isFile()
						&& configFile.canRead()){
					InputStream input = new FileInputStream(configFile);
					config = new Properties();
					config.load(input);
				}
				
			}
		}catch(Exception e){
			logger.debug("{}",e);
			//default set
			/*config = new Properties();
			config.setProperty("protocol", "TLSV1");
			config.setProperty("serverCer", "./certificate/server.jks");
			config.setProperty("serverCerPwd", "1234sp");
			config.setProperty("serverKeyPwd", "1234kp");
			config.setProperty("serverTrustCer", "./certificate/serverTrust.jks");
			config.setProperty("serverTrustCerPwd", "1234sp");
			config.setProperty("clientCer", "./certificate/client.jks");
			config.setProperty("clientCerPwd", "1234sp");
			config.setProperty("clientKeyPwd", "1234kp");
			config.setProperty("clientTrustCer", "./certificate/clientTrust.jks");
			config.setProperty("clientTrustCerPwd", "1234sp");
			config.setProperty("serverListenPort", "10000");
			config.setProperty("serverThreadPoolSize", "5");
			config.setProperty("serverRequestQueueSize", "10");
			config.setProperty("socketStreamEncoding", "UTF-8");*/
		}
		return config;
	}
	
	public static void main(String[] args) {
		System.out.println(getConfig().getProperty("serverListenPort"));
		System.out.println(getConfig().getProperty("serverCer"));
	}
}