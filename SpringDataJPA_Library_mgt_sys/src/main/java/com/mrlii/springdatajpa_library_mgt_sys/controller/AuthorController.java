package com.mrlii.springdatajpa_library_mgt_sys.controller;

import com.mrlii.springdatajpa_library_mgt_sys.dto.request.CreateAuthorRequest;
import com.mrlii.springdatajpa_library_mgt_sys.dto.response.ApiResponse;
import com.mrlii.springdatajpa_library_mgt_sys.dto.response.AuthorResponse;
import com.mrlii.springdatajpa_library_mgt_sys.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<AuthorResponse>> createAuthor(@RequestBody CreateAuthorRequest request){
        ApiResponse<AuthorResponse> response = authorService.createAuthor(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id){
        AuthorResponse authorResponse = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable Long id){
        ApiResponse<Void> response = authorService.deleteAuthor(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<AuthorResponse>> updateAuthor(@PathVariable Long id, @RequestBody CreateAuthorRequest request){
        ApiResponse<AuthorResponse> response = authorService.updateAuthor(id, request);
        return ResponseEntity.ok(response);
    }
}
