package com.duynvh.masterdesignpattern.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.duynvh.masterdesignpattern.BookApplication;
import com.duynvh.masterdesignpattern.entity.Author;
import com.duynvh.masterdesignpattern.factory.ConnectionFactory;
import com.duynvh.masterdesignpattern.repository.AuthorRepository;

public class AuthorRepositoryImpl implements AuthorRepository {
	private final ConnectionFactory connectionFactory = BookApplication.getInstance().getConnectionFactory();

	@Override
	public void save(final Author author) throws Exception {
		final String query = "INSERT INTO Author (name) VALUES  (?)";
		try (Connection connection = connectionFactory.newConnection()) {
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
		}
	}
}
