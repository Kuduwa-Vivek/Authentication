package com.authentication.Authentication.repo;

import com.authentication.Authentication.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Optional<Book> findByTitle(String title);

  Optional<Book> findByAuthor(String author);
}
