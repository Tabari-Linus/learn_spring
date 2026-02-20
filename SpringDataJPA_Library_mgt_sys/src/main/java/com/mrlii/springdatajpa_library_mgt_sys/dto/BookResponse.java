package com.mrlii.springdatajpa_library_mgt_sys.dto;

public record BookResponse(
        Long id,
        String title,
        String isbn,
        String authorName
) {
}
