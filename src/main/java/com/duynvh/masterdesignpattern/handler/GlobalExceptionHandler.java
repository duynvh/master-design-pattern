package com.duynvh.masterdesignpattern.handler;

import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandler implements Handler<Exception, ResponseEntity> {
	@Override
	public ResponseEntity handle(Exception input) {
		return ResponseEntity.badRequest().body(input.getMessage());
	}
}
