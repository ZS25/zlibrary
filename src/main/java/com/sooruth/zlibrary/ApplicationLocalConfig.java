package com.sooruth.zlibrary;

import com.github.javafaker.Faker;
import com.sooruth.zlibrary.entity.Book;
import com.sooruth.zlibrary.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Profile("local")
@Configuration
@PropertySource("messages.properties")
public class ApplicationLocalConfig {

    private final Logger LOG = LoggerFactory.getLogger(ApplicationLocalConfig.class);

    @Bean
    /**
     * @apiNote This method will execute just after the context is created to initialise the database with some records.
     */
    public CommandLineRunner commandLineRunner(JdbcConnectionDetails jdbcConnectionDetails, BookRepository bookRepository){
        return args -> {
            displayDatabaseConnectionDetails(jdbcConnectionDetails);
            fillBookTableAtStartup(bookRepository);
        };
    }

    private void displayDatabaseConnectionDetails(JdbcConnectionDetails jdbcConnectionDetails) {
        String databaseConnectionDetails = StringTemplate.STR."""
                class: \{jdbcConnectionDetails.getClass().getName()}
                JDBC URL: \{jdbcConnectionDetails.getJdbcUrl()}
                Username: \{jdbcConnectionDetails.getUsername()}
                Password: \{jdbcConnectionDetails.getPassword()}
                """;
        LOG.info(databaseConnectionDetails);
    }

    private void fillBookTableAtStartup(BookRepository bookRepository) {
        Faker faker = new Faker();
        List<Book> bookList = new ArrayList<>();

        for(int counter=0; counter<10; counter++) {
            Book book = new Book();
            book.setIsbn(ThreadLocalRandom.current().nextLong(10L, 100L));
            book.setCategory(faker.book().genre());
            book.setTitle(faker.book().title());
            book.setAuthor(faker.book().author());
            book.setDateCreated(LocalDateTime.now());

            bookList.add(book);
        }
        bookRepository.saveAll(bookList);

        LOG.info(String.format("Number of records in T_BOOK at startup: %d", bookRepository.count()));
    }

}
