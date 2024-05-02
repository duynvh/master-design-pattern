package com.duynvh.masterdesignpattern.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.duynvh.masterdesignpattern.BookApplication;
import com.duynvh.masterdesignpattern.entity.Book;
import com.duynvh.masterdesignpattern.factory.ConnectionFactory;
import com.duynvh.masterdesignpattern.pool.ConnectionPool;
import com.duynvh.masterdesignpattern.repository.BookRepository;

public class BookRepositoryImpl implements BookRepository {
	private final ConnectionFactory connectionFactory = BookApplication.getInstance().getConnectionFactory();
	@Override
	public void save(final Book book) throws Exception {
		final String query =
				"INSERT INTO Book (name, authorId, categoryId) VALUE(?, ?, ?)";
		final ConnectionPool connectionPool = BookApplication.getInstance().getConnectionPool();
		final Connection connection = connectionPool.provide();
		try {
			try(
					PreparedStatement statement = connection.prepareStatement(
							query,
							Statement.RETURN_GENERATED_KEYS
					)
			) {
				statement.setString(1, book.getName());
				statement.setLong(2, book.getAuthorId());
				statement.setLong(3, book.getCategoryId());
				statement.executeUpdate();
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						book.setId(generatedKeys.getLong(1));
					}
				}
			}
		} finally {
			connectionPool.pushBack(connection);
		}
	}
}
