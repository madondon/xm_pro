package com.offcn.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ��Ŀȫ��ʹ�ù�����
 * @author IBM
 *
 */
public class MiTools {
	
	/**
	 * �����ַ�����ʽ����
	 * @return String
	 */
	public static String getStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sdf.format(new Date());
		return d;
	}
	
}
