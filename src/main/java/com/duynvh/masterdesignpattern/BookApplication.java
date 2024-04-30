package com.duynvh.masterdesignpattern;

import com.duynvh.masterdesignpattern.factory.ConnectionFactory;
import com.duynvh.masterdesignpattern.factory.EntityFactory;
import com.duynvh.masterdesignpattern.factory.impl.ConnectionFactoryImpl;
import com.duynvh.masterdesignpattern.factory.impl.EntityFactoryImpl;
import com.duynvh.masterdesignpattern.repository.DatabaseContext;
import com.duynvh.masterdesignpattern.repository.impl.DatabaseContextImpl;

import lombok.Getter;

public final class BookApplication {
	@Getter
	private final EntityFactory entityFactory;
	@Getter
	private final ConnectionFactory connectionFactory;
	@Getter
	private final DatabaseContext databaseContext;
	private static final BookApplication INSTANCE = new BookApplication();

	private BookApplication() {
		entityFactory = new EntityFactoryImpl();
		connectionFactory = new ConnectionFactoryImpl();
		databaseContext = new DatabaseContextImpl();
	}

	public static BookApplication getInstance() {
		return INSTANCE;
	}
}
