package com.mrlii.springdatajpa_library_mgt_sys.repository;

import com.mrlii.springdatajpa_library_mgt_sys.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}