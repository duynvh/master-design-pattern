package com.duynvh.masterdesignpattern.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.duynvh.masterdesignpattern.BookApplication;
import com.duynvh.masterdesignpattern.entity.Category;
import com.duynvh.masterdesignpattern.factory.ConnectionFactory;
import com.duynvh.masterdesignpattern.pool.ConnectionPool;
import com.duynvh.masterdesignpattern.repository.CategoryRepository;

public class CategoryRepositoryImpl implements CategoryRepository {
	private final ConnectionFactory connectionFactory =
			BookApplication.getInstance().getConnectionFactory();

	@Override
	public void save(Category author) throws Exception {
		final String query = "INSERT INTO Category (name) VALUE(?)";
		final ConnectionPool connectionPool = BookApplication
				.getInstance()
				.getConnectionPool();
		final Connection connection = connectionPool.provide();
		try {
			try(
					PreparedStatement statement = connection.prepareStatement(
							query,
							Statement.RETURN_GENERATED_KEYS
					)
			) {
				statement.setString(1, author.getName());
				statement.executeUpdate();
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						author.setId(generatedKeys.getLong(1));
					}
				}
			}
		} finally {
			connectionPool.pushBack(connection);
		}
	}
}
