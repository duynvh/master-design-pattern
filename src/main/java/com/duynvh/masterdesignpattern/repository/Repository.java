package com.duynvh.masterdesignpattern.repository;

public interface Repository<T> {
	void save(T entity) throws Exception;
}
