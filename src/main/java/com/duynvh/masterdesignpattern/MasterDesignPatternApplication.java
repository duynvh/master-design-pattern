package com.duynvh.masterdesignpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		SpringApplication.run(MasterDesignPatternApplication.class, args);
		final BookApplication application = BookApplication.getInstance();
		final DatabaseContext databaseContext = application.getDatabaseContext();
		final EntityFactory entityFactory = application.getEntityFactory();

		final AuthorRepository authorRepository = databaseContext.newRepository(
				Author.class
		);
		final CategoryRepository categoryRepository = databaseContext.newRepository(
				Category.class
		);
		final BookRepository bookRepository = databaseContext.newRepository(
				Book.class
		);

		final Author author = entityFactory.newEntity(Author.class, "Dzung");
		authorRepository.save(author);

		final Category category = entityFactory.newEntity(Category.class, "Technology");
		categoryRepository.save(category);

		final Book book = entityFactory.newEntity(Book.class, "Design Patterns");
		book.setAuthorId(author.getId());
		book.setCategoryId(category.getId());
		bookRepository.save(book);
	}

}
