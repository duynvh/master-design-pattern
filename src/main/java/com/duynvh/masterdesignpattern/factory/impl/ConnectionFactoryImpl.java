package com.duynvh.masterdesignpattern.factory.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import com.duynvh.masterdesignpattern.factory.ConnectionFactory;

public class ConnectionFactoryImpl implements ConnectionFactory {
	@Override
	public Connection newConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/book_market",
				"root",
				"123456"
		);
	}
}
