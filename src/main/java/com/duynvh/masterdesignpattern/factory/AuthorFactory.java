package com.duynvh.masterdesignpattern.factory;

import com.duynvh.masterdesignpattern.builder.AuthorBuilder;
import com.duynvh.masterdesignpattern.entity.Author;

public interface AuthorFactory {
	Author newAuthor(String name);

	AuthorBuilder newAuthorBuilder();
}
