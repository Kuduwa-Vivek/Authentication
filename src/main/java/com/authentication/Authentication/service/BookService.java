package com.authentication.Authentication.service;

import com.authentication.Authentication.entity.Book;
import com.authentication.Authentication.repo.BookRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

  @Autowired private BookRepository bookRepository;

  private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);

  public Book getByName(Book name) {
    return name;
  }

  public Book updateBookById(Long id, Book book) {
    logger.info("Received request to update book with ID: {}", id);
    Optional<Book> byId = bookRepository.findById(id);
    if (byId.isEmpty()) {
      logger.error("Book with ID: {} not found. Cannot update.", id);
      throw new RuntimeException("Book not find with id: " + id);
    }
    Book book1 = byId.get();
    book1.setAuthor(book.getAuthor());
    book1.setTitle(book.getTitle());
    Book updatedBook = bookRepository.save(book1);
    logger.info(
        "Successfully updated book with ID: {}. New Author: {}, New Title: {}",
        id,
        updatedBook.getAuthor(),
        updatedBook.getTitle());
    return updatedBook;
  }
}
