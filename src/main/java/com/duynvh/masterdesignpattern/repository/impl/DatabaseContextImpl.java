package com.duynvh.masterdesignpattern.repository.impl;

import com.duynvh.masterdesignpattern.entity.Author;
import com.duynvh.masterdesignpattern.entity.Book;
import com.duynvh.masterdesignpattern.entity.Category;
import com.duynvh.masterdesignpattern.repository.DatabaseContext;
import com.duynvh.masterdesignpattern.repository.Repository;

public class DatabaseContextImpl implements DatabaseContext {
	@SuppressWarnings("unchecked")
	@Override
	public <E, R extends Repository<E>> R newRepository(final Class<E> entityType) {
		if (entityType == Author.class) {
			return (R) new AuthorRepositoryImpl();
		}

		if (entityType == Book.class) {
			return (R) new BookRepositoryImpl();
		}

		if (entityType == Category.class) {
			return (R) new CategoryRepositoryImpl();
		}

		throw new IllegalArgumentException("there is no repository for " + entityType.getName());
	}
}
