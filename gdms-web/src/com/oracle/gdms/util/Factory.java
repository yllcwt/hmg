package com.oracle.gdms.util;

import java.util.ResourceBundle;

//import com.uplooking.app.dao.UserDao;

public class Factory {

	private static ResourceBundle rb;
	
	static {
		rb = ResourceBundle.getBundle("config/obj");
	}
	private Factory() {}
	
//	private static Factory fac = new Factory();
	private static Factory fac;
	public static Factory getInstance() {
		fac = fac==null ? new Factory() : fac;
		return fac;
	}

	public Object getObject(String key) {
		//读取配置文件，从配置文件中找到key对应的class路径和名称
		String className = rb.getString(key);
		
		Object o = null;
		try {
			o = Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return o;
	}
	
}
