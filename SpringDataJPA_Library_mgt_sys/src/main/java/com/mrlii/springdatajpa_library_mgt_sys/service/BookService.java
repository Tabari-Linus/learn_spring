package com.mrlii.springdatajpa_library_mgt_sys.service;

import com.mrlii.springdatajpa_library_mgt_sys.dto.response.BookResponse;
import com.mrlii.springdatajpa_library_mgt_sys.entity.Book;
import com.mrlii.springdatajpa_library_mgt_sys.exception.ResourceNotFoundException;
import com.mrlii.springdatajpa_library_mgt_sys.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        )).orElseThrow(
                () -> new ResourceNotFoundException("Book not found with id: " + id)
        );
    }

    public void deleteBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            bookRepository.delete(book.get());
        }else{
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    public Book updateBook(Long id, String title, String isbn){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            Book updatedBook = book.get();
            updatedBook.setTitle(title);
            updatedBook.setIsbn(isbn);

            return bookRepository.save(updatedBook);
        }else{
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    public Page<BookResponse> getBooks(int pageNumber, int pageSize){

        Sort sort = Sort.by("title").ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Book> books = bookRepository.findAll(pageable);

        return books.map(
                book -> new BookResponse(
                                book.getId(),
                                book.getTitle(),
                                book.getIsbn(),
                                book.getAuthor().getName()
                        )
        );
    }

}