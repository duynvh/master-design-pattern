package com.duynvh.masterdesignpattern.factory;

import com.duynvh.masterdesignpattern.builder.BookBuilder;
import com.duynvh.masterdesignpattern.entity.Book;

public interface BookFactory {
	Book newBook(String name);

	BookBuilder newBookBuilder();
}
