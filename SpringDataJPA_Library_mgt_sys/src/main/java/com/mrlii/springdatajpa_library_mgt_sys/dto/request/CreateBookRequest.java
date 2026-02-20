package com.mrlii.springdatajpa_library_mgt_sys.dto.request;

public record CreateBookRequest (
        String title,
        String isbn,
        Long authorId
){
}
