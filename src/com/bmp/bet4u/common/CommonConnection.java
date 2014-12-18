package com.bmp.bet4u.common;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CommonConnection {
	
	private static DriverManagerDataSource ds;
	
	public static DataSource getDataSource() {
		if (ds == null) {
			//MYSQL Driver load
			ds = new DriverManagerDataSource();
			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl("jdbc:mysql://localhost:3306/bet4u");
			ds.setUsername("bet4u");
			ds.setPassword("bet4u");
		}
		return ds;
	}
}
