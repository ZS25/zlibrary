package com.sooruth.zlibrary.controller;

import com.sooruth.zlibrary.entity.Book;
import com.sooruth.zlibrary.mapper.BookMapper;
import com.sooruth.zlibrary.record.BookRecord;
import com.sooruth.zlibrary.service.BookService;
import com.sooruth.zlibrary.service.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/books")
public final class BookControllerImpl implements BookController {

    private final Logger LOG = LoggerFactory.getLogger(BookControllerImpl.class);

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookControllerImpl(BookServiceImpl bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Override
    public Page<BookRecord> getAll(int page, int size) {
        return bookService.readAll(page, size)
                .map(bookMapper::bookToBookRecord);
    }

    @Override
    public BookRecord getById(Long id) {
        return bookMapper.bookToBookRecord(bookService.read(id));
    }

    @Override
    public ResponseEntity<String> save(BookRecord model) {
        Long savedBookId = bookService.create(bookMapper.bookRecordToBook(model));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedBookId).toUri()).build();
    }

    @Override
    public BookRecord modify(BookRecord model) {
        Book book = bookService.update(bookMapper.bookRecordToBook(model));
        return bookMapper.bookToBookRecord(book);
    }

    @Override
    public void delete(Long id) {
        bookService.delete(id);
    }
}
