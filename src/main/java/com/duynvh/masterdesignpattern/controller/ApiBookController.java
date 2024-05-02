package com.duynvh.masterdesignpattern.controller;

import static io.micrometer.common.util.StringUtils.isBlank;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duynvh.masterdesignpattern.BookApplication;
import com.duynvh.masterdesignpattern.builder.AuthorBuilder;
import com.duynvh.masterdesignpattern.builder.BookBuilder;
import com.duynvh.masterdesignpattern.entity.Author;
import com.duynvh.masterdesignpattern.entity.Book;
import com.duynvh.masterdesignpattern.factory.EntityFactory;
import com.duynvh.masterdesignpattern.handler.ChainOfResponsibility;
import com.duynvh.masterdesignpattern.repository.BookRepository;
import com.duynvh.masterdesignpattern.request.AddAuthorRequest;
import com.duynvh.masterdesignpattern.request.AddBookRequest;
import com.duynvh.masterdesignpattern.response.AddAuthorResponse;
import com.duynvh.masterdesignpattern.response.AddBookResponse;

@RestController
@RequestMapping(value = "/books")
public class ApiBookController {
	private final BookApplication bookApplication =
			BookApplication.getInstance();

	private final EntityFactory entityFactory =
			bookApplication.getEntityFactory();

	private final BookRepository bookRepository = bookApplication.getDatabaseContext().newRepository(Book.class);

	@PostMapping
	public ResponseEntity addBook(
			@RequestBody AddBookRequest request
	) {
		return new ChainOfResponsibility()
				.addFirstVoidHandler(() -> {
					final Map<String, String> errors = new HashMap<>();
					if (isBlank(request.getBookName())) {
						throw new IllegalArgumentException("Book name is required");
					}
				})
				.addFirstHandle(() -> {
					final Book book = entityFactory
							.newEntityBuilder(BookBuilder.class)
							.name(request.getBookName())
							.authorId(request.getAuthorId())
							.categoryId(request.getCategoryId())
							.build();
					bookRepository.save(book);
					return new AddBookResponse(book.getId());
				})
				.handle();
	}
}
