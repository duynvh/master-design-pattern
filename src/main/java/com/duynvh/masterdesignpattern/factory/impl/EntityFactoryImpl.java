package com.duynvh.masterdesignpattern.factory.impl;

import com.duynvh.masterdesignpattern.entity.Author;
import com.duynvh.masterdesignpattern.entity.Book;
import com.duynvh.masterdesignpattern.entity.Category;
import com.duynvh.masterdesignpattern.factory.AuthorFactory;
import com.duynvh.masterdesignpattern.factory.BookFactory;
import com.duynvh.masterdesignpattern.factory.CategoryFactory;
import com.duynvh.masterdesignpattern.factory.EntityFactory;

public class EntityFactoryImpl implements EntityFactory {
	private final AuthorFactory authorFactory = new AuthorFactoryImpl();
	private final CategoryFactory categoryFactory = new CategoryFactoryImpl();
	private final BookFactory bookFactory = new BookFactoryImpl();

	@SuppressWarnings("unchecked")
	@Override
	public <E> E newEntity(final Class<E> entityType, final String name) {
		if (entityType == Author.class) {
			return (E) authorFactory.newAuthor(name);
		}

		if (entityType == Category.class) {
			return (E) categoryFactory.newCategory(name);
		}

		if (entityType == Book.class) {
			return (E) bookFactory.newBook(name);
		}

		throw new IllegalArgumentException("there is no factory for: " + entityType);
	}
}
