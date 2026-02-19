package com.mrlii.springdatajpa_library_mgt_sys.service;

import com.mrlii.springdatajpa_library_mgt_sys.entity.Book;
import com.mrlii.springdatajpa_library_mgt_sys.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(String title, String isbn) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setIsbn(isbn);

        return bookRepository.save(newBook);
    }
}