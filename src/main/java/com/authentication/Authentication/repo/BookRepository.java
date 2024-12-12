package com.authentication.Authentication.repo;

import com.authentication.Authentication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
