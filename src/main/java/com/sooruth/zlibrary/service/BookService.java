package com.sooruth.zlibrary.service;

import com.sooruth.zlibrary.entity.Book;

public sealed interface BookService extends EntityService<Book> permits BookServiceImpl{

}
