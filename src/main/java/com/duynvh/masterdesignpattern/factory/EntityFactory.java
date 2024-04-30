package com.duynvh.masterdesignpattern.factory;

import com.duynvh.masterdesignpattern.builder.Builder;

public interface EntityFactory {
	<E> E newEntity(Class<E> entityType, String name);

	<E, B extends Builder<E>> B newEntityBuilder(Class<B> builderType);
}
