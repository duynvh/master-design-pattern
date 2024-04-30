package com.duynvh.masterdesignpattern.factory.impl;

import com.duynvh.masterdesignpattern.builder.BookBuilder;
import com.duynvh.masterdesignpattern.entity.Book;
import com.duynvh.masterdesignpattern.factory.BookFactory;

public class BookFactoryImpl implements BookFactory {
	@Override
	public Book newBook(String name) {
		final Book book = new Book();
		book.setName(name);
		return book;
	}

	@Override
	public BookBuilder newBookBuilder() {
		return new BookBuilder();
	}
}
