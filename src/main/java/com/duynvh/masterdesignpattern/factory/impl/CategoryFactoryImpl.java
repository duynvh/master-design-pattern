package com.duynvh.masterdesignpattern.factory.impl;

import com.duynvh.masterdesignpattern.builder.CategoryBuilder;
import com.duynvh.masterdesignpattern.entity.Category;
import com.duynvh.masterdesignpattern.factory.CategoryFactory;

public class CategoryFactoryImpl implements CategoryFactory {
	@Override
	public Category newCategory(String name) {
		final Category category = new Category();
		category.setName(name);
		return category;
	}

	@Override
	public CategoryBuilder newCategoryBuilder() {
		return new CategoryBuilder();
	}
}
