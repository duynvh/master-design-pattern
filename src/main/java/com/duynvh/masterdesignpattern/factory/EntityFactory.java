package com.duynvh.masterdesignpattern.factory;

public interface EntityFactory {
	<E> E newEntity(Class<E> entityType, String name);
}
