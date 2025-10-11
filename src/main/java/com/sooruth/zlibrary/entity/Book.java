package com.sooruth.zlibrary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Book")
@Table(name = "T_BOOK", uniqueConstraints = {
        @UniqueConstraint(name = "T_BOOK_ID_UNIQUE", columnNames = "ID")
})
public class Book {


    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ISBN", nullable = false, columnDefinition = "INTEGER")
    private Long isbn;

    @Column(name = "CATEGORY", nullable = false, columnDefinition = "VARCHAR(50)")
    private String category;

    @Column(name = "TITLE", nullable = false, columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(name = "AUTHOR", nullable = false, columnDefinition = "VARCHAR(50)")
    private String author;

    @Column(name = "DATE_CREATED", nullable = false, updatable = false, columnDefinition="TIMESTAMP")
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime dateCreated;

    @Column(name = "DATE_UPDATED", columnDefinition="TIMESTAMP")
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime dateUpdated;

    public Book() {
    }

    public Book(Long isbn, String category, String title, String author, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.isbn = isbn;
        this.category = category;
        this.title = title;
        this.author = author;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(isbn, book.isbn) && Objects.equals(category, book.category) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(dateCreated, book.dateCreated) && Objects.equals(dateUpdated, book.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, category, title, author, dateCreated, dateUpdated);
    }

    @Override
    public String toString() {
        return """
        Book{id=%s, isbn=%s, category='%s', title='%s', author='%s', dateCreated=%s, dateUpdated=%s}
        """.formatted(
                id,
                isbn,
                category,
                title,
                author,
                dateCreated,
                dateUpdated);
    }
}
