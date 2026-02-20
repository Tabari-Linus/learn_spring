package com.mrlii.springdatajpa_library_mgt_sys.service;


import com.mrlii.springdatajpa_library_mgt_sys.dto.response.ApiResponse;
import com.mrlii.springdatajpa_library_mgt_sys.dto.response.AuthorResponse;
import com.mrlii.springdatajpa_library_mgt_sys.dto.request.CreateAuthorRequest;
import com.mrlii.springdatajpa_library_mgt_sys.entity.Author;
import com.mrlii.springdatajpa_library_mgt_sys.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public ApiResponse<AuthorResponse> createAuthor(CreateAuthorRequest request){
        Author newAuthor = new Author();
        newAuthor.setName(request.name());
        newAuthor.setNationality(request.nationality());

        Author savedAuthor = authorRepository.save(newAuthor);

        AuthorResponse authorResponse = new AuthorResponse(
                savedAuthor.getName(),
                savedAuthor.getNationality()
        );
        return ApiResponse.success("Author created successfully", authorResponse);
    }

    public AuthorResponse getAuthorById(Long id){
        Optional<Author> author = authorRepository.findById(id);
        return author.map(value -> new AuthorResponse(
                value.getName(),
                value.getNationality()
                )).orElse(null);
    }

    public ApiResponse<Void> deleteAuthor(Long id){
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent()){
            authorRepository.delete(author.get());
            return ApiResponse.success("Author deleted successfully");
        }
        return ApiResponse.error("Author not found");
    }

    public ApiResponse<AuthorResponse> updateAuthor(Long id, CreateAuthorRequest request){
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent()){
            Author updatedAuthor = author.get();
            updatedAuthor.setName(request.name());
            updatedAuthor.setNationality(request.nationality());

            Author savedAuthor = authorRepository.save(updatedAuthor);

            AuthorResponse authorResponse = new AuthorResponse(
                    savedAuthor.getName(),
                    savedAuthor.getNationality()
            );
            return ApiResponse.success("Author updated successfully", authorResponse);
        }
        return ApiResponse.error("Author not found");
    }
}
