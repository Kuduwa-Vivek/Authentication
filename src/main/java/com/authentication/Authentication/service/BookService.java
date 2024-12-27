package com.authentication.Authentication.service;

import com.authentication.Authentication.entity.Book;
import com.authentication.Authentication.exception.BookNotFoundException;
import com.authentication.Authentication.repo.BookRepository;
import java.util.Objects;
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

  //  public Book findBook(Long id, String title, String author) {
  //    if (id != null) {
  //      bookRepository
  //          .findById(id)
  //          .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " Not Found."));
  //    } else if (title != null) {
  //      return bookRepository.findByTitle(title).orElseThrow(() -> new BookNotFoundException("Book
  // with Title " + title + " Not Found."));
  //    } else if (author != null) {
  //      return bookRepository.findByAuthor(author).orElseThrow(() -> new
  // BookNotFoundException("Book with author " + author + " Not Found."))
  //    } else {
  //      throw new IllegalArgumentException("At least one parameter (id, title, or author) must be
  // provided");
  //    }
  //  }

  public Book findBook(Long id, String title, String author) {
    if (Objects.nonNull(id)) {
      return bookRepository
          .findById(id)
          .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    } else if (Objects.nonNull(title)) {
      return bookRepository
          .findByTitle(title)
          .orElseThrow(
              () -> new BookNotFoundException("Book with title '" + title + "' not found"));
    } else if (Objects.nonNull(author)) {
      return bookRepository
          .findByAuthor(author)
          .orElseThrow(
              () -> new BookNotFoundException("Book with author '" + author + "' not found"));
    } else {
      throw new IllegalArgumentException(
          "At least one parameter (id, title, or author) must be provided");
    }
  }

  public Book findBookByTitle(String title) {
    return bookRepository
        .findByTitle(title)
        .orElseThrow(() -> new BookNotFoundException("Book with title '" + title + "' not found"));
  }

  public Book findBookByAuthor(String author) {
    return bookRepository
        .findByAuthor(author)
        .orElseThrow(
            () -> new BookNotFoundException("Book with author '" + author + "' not found"));
  }

  public Book findBookById(Long id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
  }
}
