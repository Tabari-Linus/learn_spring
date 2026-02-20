package com.mrlii.springdatajpa_library_mgt_sys.repository;

import com.mrlii.springdatajpa_library_mgt_sys.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
