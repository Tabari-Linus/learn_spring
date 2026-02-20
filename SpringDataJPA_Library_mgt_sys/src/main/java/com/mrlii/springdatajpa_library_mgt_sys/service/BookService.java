package com.mrlii.springdatajpa_library_mgt_sys.service;

import com.mrlii.springdatajpa_library_mgt_sys.dto.BookResponse;
import com.mrlii.springdatajpa_library_mgt_sys.entity.Book;
import com.mrlii.springdatajpa_library_mgt_sys.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(String title, String isbn) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setIsbn(isbn);

        return bookRepository.save(newBook);
    }

    public BookResponse getBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> new BookResponse(
                value.getId(),
                value.getTitle(),
                value.getIsbn(),
                value.getAuthor().getName()
        )).orElse(null);
    }
}