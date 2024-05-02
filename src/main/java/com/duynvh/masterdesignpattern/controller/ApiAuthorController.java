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
import com.duynvh.masterdesignpattern.entity.Author;
import com.duynvh.masterdesignpattern.factory.EntityFactory;
import com.duynvh.masterdesignpattern.handler.ChainOfResponsibility;
import com.duynvh.masterdesignpattern.repository.AuthorRepository;
import com.duynvh.masterdesignpattern.request.AddAuthorRequest;
import com.duynvh.masterdesignpattern.response.AddAuthorResponse;

@RestController
@RequestMapping(value = "/authors")
public class ApiAuthorController {
	private final BookApplication bookApplication =
			BookApplication.getInstance();

	private final EntityFactory entityFactory =
			bookApplication.getEntityFactory();

	private final AuthorRepository authorRepository =
			bookApplication
					.getDatabaseContext()
					.newRepository(Author.class);

	@PostMapping
	public ResponseEntity addAuthor(
			@RequestBody AddAuthorRequest request
	) {
		return new ChainOfResponsibility()
				.addFirstVoidHandler(() -> {
					final Map<String, String> errors = new HashMap<>();
					if (isBlank(request.getAuthorName())) {
						throw new IllegalArgumentException("Author name is required");
					}
				})
				.addFirstHandle(() -> {
					final Author author = entityFactory
							.newEntityBuilder(AuthorBuilder.class)
							.name(request.getAuthorName())
							.build();
					authorRepository.save(author);
					return new AddAuthorResponse(author.getId());
				})
				.handle();
	}
}
