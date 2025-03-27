package com.sooruth.zlibrary.controller;

import com.sooruth.zlibrary.record.BookRecord;

public sealed interface BookController extends ModelController<BookRecord> permits BookControllerImpl {

}
