package com.duynvh.masterdesignpattern.factory;

import com.duynvh.masterdesignpattern.entity.Category;

public interface CategoryFactory {
	Category newCategory(String name);
}
