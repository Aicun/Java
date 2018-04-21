package com.lac.spider.db;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MyDataSource {

	public static DataSource getDataSource(String dbUrl) {
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setUser("root");
		mysqlDS.setPassword("root");
		mysqlDS.setUrl(dbUrl);
        return mysqlDS;
	}
}
