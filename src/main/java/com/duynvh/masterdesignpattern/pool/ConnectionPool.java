package com.duynvh.masterdesignpattern.pool;

import java.sql.Connection;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

import com.duynvh.masterdesignpattern.BookApplication;

public class ConnectionPool {
	private final LinkedBlockingDeque<Connection> connectionQueue = new LinkedBlockingDeque<>();
	private final AtomicInteger numberOfCreatedConnections = new AtomicInteger(0);
	private static final int MAX_CONNECTIONS = 16;

	public Connection provide() throws Exception {
		synchronized (this) {
			if (numberOfCreatedConnections.get() < MAX_CONNECTIONS) {
				final Connection connection = BookApplication
						.getInstance()
						.getConnectionFactory()
						.newConnection();
				numberOfCreatedConnections.incrementAndGet();
				return connection;
			}
		}

		return connectionQueue.take();
	}

	public void pushBack(Connection connection) {
		connectionQueue.offer(connection);
	}
}
