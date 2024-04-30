package com.duynvh.masterdesignpattern.factory;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection newConnection() throws Exception;
}
