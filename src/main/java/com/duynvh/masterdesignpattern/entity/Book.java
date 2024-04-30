package com.duynvh.masterdesignpattern.entity;

import lombok.Data;

@Data
public class Book {
	private long id;
	private String name;
	private long authorId;
	private long categoryId;
}
