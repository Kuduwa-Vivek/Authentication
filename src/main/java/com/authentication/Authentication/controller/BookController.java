package com.authentication.Authentication.controller;

import com.authentication.Authentication.entity.Book;
import com.authentication.Authentication.repo.BookRepository;
import com.authentication.Authentication.service.BookService;
import java.util.List;
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

@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired private BookRepository bookRepository;

  @Autowired private BookService bookService;

  @GetMapping
  public List<Book> getAll() {
    return bookRepository.findAll();
  }

  @PostMapping("/addBook")
  public Book addBook(@RequestBody Book book) {
    return bookRepository.save(book);
  }

  @PutMapping("/book/{id}")
  public Book updateBookById(@PathVariable Long id, @RequestBody Book book) {
    return bookService.updateBookById(id, book);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteById(@RequestParam Long id) {
    if (!bookRepository.existsById(id)) {
      throw new RuntimeException("Book not found by id: " + id);
    }
    bookRepository.deleteById(id);
    return "Book deleted successfully: " + id;
  }
}
