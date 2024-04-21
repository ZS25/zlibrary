package com.sooruth.zlibrary.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record BookRecord(
        Long id,
        @NotNull
        Long isbn,
        @NotBlank(message = "category cannot be blank")
        @Size(min = 5, max = 50)
        String category,
        @NotBlank(message = "title cannot be blank")
        @Size(min = 2, max = 50)
        String title,
        @NotBlank(message = "author cannot be blank")
        @Size(min = 5, max = 50)
        String author,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated) {
}
