package com.sooruth.zlibrary.controller;

import com.sooruth.zlibrary.mapper.BookMapper;
import com.sooruth.zlibrary.record.BookRecord;
import com.sooruth.zlibrary.service.BookService;
import com.sooruth.zlibrary.service.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/books")
public final class BookControllerImpl implements BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookControllerImpl(BookServiceImpl bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Override
    public Page<BookRecord> readAll(int page, int size) {
        return bookService.readAll(page, size)
                .map(bookMapper::bookToBookRecord);
    }

    @Override
    public BookRecord readById(Long id) {
        return bookMapper.bookToBookRecord(bookService.read(id));
    }

    @Override
    public ResponseEntity<EntityModel> create(BookRecord model) {
        Long savedBookId = bookService.create(bookMapper.bookRecordToBook(model));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBookId)
                .toUri();

        Link selfLink = linkTo(methodOn(BookControllerImpl.class).readById(savedBookId)).withSelfRel();
        EntityModel<BookRecord> resource = EntityModel.of(model, selfLink);
        return ResponseEntity.created(location).body(resource);
    }

    @Override
    public void update(BookRecord model) {
        bookService.update(bookMapper.bookRecordToBook(model));
    }

    @Override
    public void delete(Long id) {
        bookService.delete(id);
    }
}
