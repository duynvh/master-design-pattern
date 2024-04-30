package com.duynvh.masterdesignpattern.factory.impl;

import com.duynvh.masterdesignpattern.entity.Author;
import com.duynvh.masterdesignpattern.factory.AuthorFactory;

public class AuthorFactoryImpl implements AuthorFactory {
	@Override
	public Author newAuthor(String name) {
		final Author author = new Author();
		author.setName(name);
		return author;
	}
}
