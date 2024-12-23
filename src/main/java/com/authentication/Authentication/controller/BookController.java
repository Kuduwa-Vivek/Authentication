package com.authentication.Authentication.controller;

import com.authentication.Authentication.entity.Book;
import com.authentication.Authentication.repo.BookRepository;
import com.authentication.Authentication.service.BookService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired private BookRepository bookRepository;

  @Autowired private BookService bookService;

  private static final Logger logger = LoggerFactory.getLogger(BookController.class);

  @GetMapping("/getAllBooks")
  public List<Book> getAll() {
    logger.info("Fetching all books from the database");

    List<Book> books = bookRepository.findAll();
    if (books.isEmpty()) {
      logger.warn("No books found in database");
    } else {
      logger.info("Successfully fetched {} books", books.size());
    }
    return books;
  }

  @PutMapping("/book/{id}")
  public Book updateBookById(@PathVariable Long id, @RequestBody Book book) {
    logger.info("Received request to update book with ID: {}", id);
    logger.debug("Update details: {}", book);

    Book updatedBook = bookService.updateBookById(id, book);
    if (updatedBook != null) {
      logger.info("Successfully updated book with ID {}", id);
    } else {
      logger.warn("Book with ID: {} not found, update failed", id);
    }
    return updatedBook;
  }

  @PostMapping("/addBook")
  public Book addBook(@RequestBody Book book) {
    logger.info("Received request to add new Book");
    logger.debug("Book details: {}", book);

    Book saveBook = bookRepository.save(book);
    logger.info("Book added successfully {}", saveBook);
    return saveBook;
  }

  @DeleteMapping("/delete/{id}")
  public String deleteById(@RequestParam Long id) {
    logger.info("Request to delete book by id {}", id);
    if (!bookRepository.existsById(id)) {
      logger.warn("Book with ID: {} not found. Unable to delete.", id);
      throw new RuntimeException("Book not found by id: " + id);
    }
    bookRepository.deleteById(id);
    logger.info("Book with ID: {} deleted successfully.", id);

    return "Book deleted successfully: " + id;
  }
}
