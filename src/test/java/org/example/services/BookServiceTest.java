package org.example.services;

import org.example.dao.AuthorDAO;
import org.example.dao.BookDAO;
import org.example.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class BookServiceTest {

    @Mock
    BookDAO mockBookDAO;

    @Mock
    AuthorDAO mockAuthorDAO;

    @InjectMocks
    BookService service;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdd() {
        service.addBook("Test Book", 2025);
        verify(mockBookDAO).addBook(any(Book.class));
    }

    @Test
    public void testNameUpdateBook() {
        service.updateBook(1, "Test");
        verify(mockBookDAO).updateBook(1, "Test");
    }

    @Test
    public void testNameAndYearUpdateBook() {
        service.updateBook(1, "Test", "Testov");
        verify(mockBookDAO).updateBook(1, "Test", "Testov");
    }

    @Test
    public void testDeleteBook() {
        service.deleteBook(1);
        verify(mockBookDAO).deleteBook(1);
    }

    @Test
    public void testDeleteAllBook() {
        service.deleteAllBook();
        verify(mockBookDAO).deleteAllBook();
    }

    @Test
    public void testPrintAllInfoBook() {
        service.printAllInfoBook();
        verify(mockBookDAO).getAllInfoBooks();
    }

    @Test
    public void testPrintTitleBooks() {
        service.printTitleBooks();
        verify(mockBookDAO).getTitleBooks();
    }

    @Test
    public void testPrintAllSortASCNameBook() {
        service.printAllSortASCNameBook();
        verify(mockBookDAO).getAllSortASCNameBook();
    }

    @Test
    public void testPrintAllSortDESCNameBook() {
        service.printAllSortDESCNameBook();
        verify(mockBookDAO).getAllSortDESCNameBook();
    }

    @Test
    public void testPrintAllSortASCNameAuthor() {
        service.printAllSortASCNameAuthor();
        verify(mockBookDAO).getAllSortASCNameAuthor();
    }

    @Test
    public void testPrintAllSortDESCNameAuthor() {
        service.printAllSortDESCNameAuthor();
        verify(mockBookDAO).getAllSortDESCNameAuthor();
    }

    @Test
    public void testAllSortASCYearBook() {
        service.printAllSortASCYearBook();
        verify(mockBookDAO).getAllSortASCYearBook();
    }

    @Test
    public void testPrintAllSortDESCYearBook() {
        service.printAllSortDESCYearBook();
        verify(mockBookDAO).getAllSortDESCYearBook();
    }
}