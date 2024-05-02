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
import com.duynvh.masterdesignpattern.builder.CategoryBuilder;
import com.duynvh.masterdesignpattern.entity.Category;
import com.duynvh.masterdesignpattern.factory.EntityFactory;
import com.duynvh.masterdesignpattern.handler.ChainOfResponsibility;
import com.duynvh.masterdesignpattern.repository.CategoryRepository;
import com.duynvh.masterdesignpattern.request.AddCategoryRequest;
import com.duynvh.masterdesignpattern.response.AddCategoryResponse;

@RestController
@RequestMapping(value = "/categories")
public class ApiCategoryController {
	private final BookApplication bookApplication =
			BookApplication.getInstance();

	private final EntityFactory entityFactory =
			bookApplication.getEntityFactory();

	private final CategoryRepository categoryRepository =
			bookApplication
					.getDatabaseContext()
					.newRepository(Category.class);

	@PostMapping
	public ResponseEntity addCategory(
			@RequestBody AddCategoryRequest request
	) {
		return new ChainOfResponsibility()
				.addFirstVoidHandler(() -> {
					final Map<String, String> errors = new HashMap<>();
					if (isBlank(request.getCategoryName())) {
						throw new IllegalArgumentException("Category name is required");
					}
				})
				.addFirstHandle(() -> {
					final Category category = entityFactory
							.newEntityBuilder(CategoryBuilder.class)
							.name(request.getCategoryName())
							.build();
					categoryRepository.save(category);
					return new AddCategoryResponse(category.getId());
				})
				.handle();
	}
}
