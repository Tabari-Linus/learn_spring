package com.mrlii.springdatajpa_library_mgt_sys.service;

import com.mrlii.springdatajpa_library_mgt_sys.dto.request.CreateAuthorRequest;
import com.mrlii.springdatajpa_library_mgt_sys.dto.request.CreateBookRequest;
import com.mrlii.springdatajpa_library_mgt_sys.entity.Author;
import com.mrlii.springdatajpa_library_mgt_sys.entity.Book;
import com.mrlii.springdatajpa_library_mgt_sys.repository.AuthorRepository;
import com.mrlii.springdatajpa_library_mgt_sys.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void registerAuthorAndTheirBooks(CreateAuthorRequest authorRequest, List<CreateBookRequest> bookRequests){
        Author author = new Author();
        author.setName(authorRequest.name());
        author.setNationality(authorRequest.nationality());
        Author savedAuthor = authorRepository.save(author);

        bookRequests.forEach(
                bookRequest -> {
                    Book book = new Book();
                    book.setTitle(bookRequest.title());
                    book.setIsbn(bookRequest.isbn());
                    book.setAuthor(savedAuthor);
                    bookRepository.save(book);
                }
        );
    }
}
