package com.duynvh.masterdesignpattern.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ChainOfResponsibility {
	private final List<Handler> handlers = new ArrayList<>();

	private static final GlobalExceptionHandler GLOBAL_EXCEPTION_HANDLER = new GlobalExceptionHandler();

	public ChainOfResponsibility addFirstVoidHandler(FirstVoidHandler handler) {
		handlers.add(handler);
		return this;
	}

	public <R> ChainOfResponsibility addFirstHandle(FirstHandler<R> handler) {
		handlers.add(handler);
		return this;
	}

	public <T, R> ChainOfResponsibility addHandle(Handler<T, R> handler) {
		handlers.add(handler);
		return this;
	}

	public <T> ChainOfResponsibility addVoidHandler(VoidHandler<T> handler) {
		handlers.add(handler);
		return this;
	}

	public ResponseEntity handle() {
		try {
			Object result = null;
			for (Handler handler : handlers) {
				result = handler.handle(result);
			}
			if (result instanceof ResponseEntity) {
				return (ResponseEntity) result;
			} else {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			return GLOBAL_EXCEPTION_HANDLER.handle(e);
		}
	}
}
