package com.authentication.Authentication.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.authentication.Authentication.entity.Book;
import com.authentication.Authentication.repo.BookRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookServiceTest {

  @InjectMocks private BookService bookService; // BookService should be injected here
  @Mock private BookRepository bookRepository; // Mock repository (or other dependencies)

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this); // Initializes the mocks
  }

  @Test
  public void testUpdateBookById() {
    Long id = 10L;
    String title = "Wings of Fire";
    String author = "APJ";

    Book updateBook = new Book(id, title, author);

    // Mock the behavior of the repository (or service)
    when(bookRepository.findById(id)).thenReturn(Optional.of(updateBook));
    when(bookRepository.save(any(Book.class))).thenReturn(updateBook);

    Book result = bookService.updateBookById(id, updateBook);

    // Assert the update
    Assertions.assertEquals(10L, result.getId());
    Assertions.assertEquals("Wings of Fire", result.getTitle());
    Assertions.assertEquals("APJ", result.getAuthor());
  }
}
