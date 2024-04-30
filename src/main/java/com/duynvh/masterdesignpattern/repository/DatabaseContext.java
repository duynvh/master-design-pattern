package com.duynvh.masterdesignpattern.repository;

public interface DatabaseContext {
	<E, R extends Repository<E>> R newRepository(Class<E> entityType);
}
