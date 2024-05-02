package com.duynvh.masterdesignpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.duynvh.masterdesignpattern.builder.AuthorBuilder;
import com.duynvh.masterdesignpattern.builder.BookBuilder;
import com.duynvh.masterdesignpattern.builder.CategoryBuilder;
import com.duynvh.masterdesignpattern.entity.Author;
import com.duynvh.masterdesignpattern.entity.Book;
import com.duynvh.masterdesignpattern.entity.Category;
import com.duynvh.masterdesignpattern.factory.EntityFactory;
import com.duynvh.masterdesignpattern.repository.AuthorRepository;
import com.duynvh.masterdesignpattern.repository.BookRepository;
import com.duynvh.masterdesignpattern.repository.CategoryRepository;
import com.duynvh.masterdesignpattern.repository.DatabaseContext;

@SpringBootApplication
public class MasterDesignPatternApplication {

	public static void main(String[] args) throws Exception {
//		new SpringApplicationBuilder(MasterDesignPatternApplication.class)
//				.web(WebApplicationType.NONE)
//				.run(args);
		SpringApplication.run(MasterDesignPatternApplication.class, args);
//		final BookApplication application = BookApplication.getInstance();
//		final DatabaseContext databaseContext = application.getDatabaseContext();
//		final EntityFactory entityFactory = application.getEntityFactory();
//
//		final AuthorRepository authorRepository = databaseContext.newRepository(
//				Author.class
//		);
//		final CategoryRepository categoryRepository = databaseContext.newRepository(
//				Category.class
//		);
//		final BookRepository bookRepository = databaseContext.newRepository(
//				Book.class
//		);
//
//		final Author author = entityFactory
//				.newEntityBuilder(AuthorBuilder.class)
//				.name("Duy")
//				.build();
//		authorRepository.save(author);
//
//		final Category category = entityFactory
//				.newEntityBuilder(CategoryBuilder.class)
//				.name("Coding")
//				.build();
//		categoryRepository.save(category);
//
//		final Book book = entityFactory
//				.newEntityBuilder(BookBuilder.class)
//				.name("Design Patterns 2")
//				.authorId(author.getId())
//				.categoryId(category.getId())
//				.build();
//		bookRepository.save(book);
//
//		final Book otherBook = book.clone();
//		otherBook.setName("Master Java");
//		bookRepository.save(otherBook);
	}

}
