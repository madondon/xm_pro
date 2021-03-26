package com.offcn.util;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 数据工具类
 * @author IBM
 *
 */
public class DBTools {
	
	public static QueryRunner qr = null;
	static {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		qr= new QueryRunner(ds);
	}
}
