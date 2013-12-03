/**
 * 
 */
package com.huishouwu.util;

/**
 * @author chentao.qu
 * @since 2012-8-14
 * 
 */
public class CustomerContextHolder {
	
	public static final String MYSQLDATASOURCE = "mysqlDataSource";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}
