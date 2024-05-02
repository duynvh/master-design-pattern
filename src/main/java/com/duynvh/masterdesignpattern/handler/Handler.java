package com.duynvh.masterdesignpattern.handler;

public interface Handler<T, R> {
	R handle(T input) throws Exception;
}
