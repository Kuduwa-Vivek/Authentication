package com.authentication.Authentication.service;

import com.authentication.Authentication.entity.Book;
import com.authentication.Authentication.repo.BookRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

  @Autowired private BookRepository bookRepository;

  public Book getByName(Book name) {
    return name;
  }

  public Book updateBookById(Long id, Book book) {
    Optional<Book> byId = bookRepository.findById(id);
    if (byId.isEmpty()) {
      throw new RuntimeException("Book not find with id: " + id);
    }
    Book book1 = byId.get();
    book1.setAuthor(book.getAuthor());
    book1.setTitle(book.getTitle());
    return bookRepository.save(book1);
  }
}
