package com.sooruth.zlibrary.service;

import com.sooruth.zlibrary.entity.Book;
import com.sooruth.zlibrary.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public final class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Long create(Book book) {
        return (bookRepository.save(book)).getId();
    }

    @Override
    public Book read(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        return bookOptional.orElseThrow(() -> new IllegalArgumentException(
                String.format("Book with ID: %d not found!", id)));
    }

    @Override
    public Page<Book> readAll(int page, int size) {
        final Sort SORT_BY_AUTHOR = Sort.by("author").ascending();
        PageRequest pageRequest = PageRequest.of(page, size, SORT_BY_AUTHOR);

        return bookRepository.findAll(pageRequest);
    }

    @Override
    public void update(Book book) {
        Book bookFromDatabase = read(book.getId());

        bookFromDatabase.setIsbn(book.getIsbn());
        bookFromDatabase.setCategory(book.getCategory());
        bookFromDatabase.setTitle(book.getTitle());
        bookFromDatabase.setAuthor(book.getAuthor());
        bookFromDatabase.setDateUpdated(LocalDateTime.now());

        bookRepository.save(bookFromDatabase);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
