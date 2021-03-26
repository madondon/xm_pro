package com.offcn.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 项目全局使用工具类
 * @author IBM
 *
 */
public class MiTools {
	
	/**
	 * 创建字符串格式日期
	 * @return String
	 */
	public static String getStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sdf.format(new Date());
		return d;
	}
	
}
