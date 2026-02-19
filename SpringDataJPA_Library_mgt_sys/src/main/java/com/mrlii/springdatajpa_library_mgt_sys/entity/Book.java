package com.mrlii.springdatajpa_library_mgt_sys.entity;

import jakarta.persistence.*; // Always use jakarta, not javax (Modern Spring Boot 3+)
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books") // Maps this class to the 'books' table
@Getter @Setter        // Using Lombok to keep code clean
public class Book {

    @Id // Marks this as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Database handles ID incrementing
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
}
