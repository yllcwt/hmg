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
		//��ȡ�����ļ����������ļ����ҵ�key��Ӧ��class·��������
		String className = rb.getString(key);
		
		Object o = null;
		try {
			o = Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return o;
	}
	
}
