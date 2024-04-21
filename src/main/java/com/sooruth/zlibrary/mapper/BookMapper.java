package com.sooruth.zlibrary.mapper;

import com.sooruth.zlibrary.entity.Book;
import com.sooruth.zlibrary.record.BookRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookRecordToBook(BookRecord bookRecord);
    BookRecord bookToBookRecord(Book book);

    List<Book> listBookRecordsToListBookss(List<BookRecord> bookRecordList);
    List<BookRecord> listBookssToListBookRecords(List<Book> bookList);
}
