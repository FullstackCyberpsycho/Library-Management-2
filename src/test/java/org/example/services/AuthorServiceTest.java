package org.example.services;

import org.example.dao.AuthorDAO;
import org.example.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    AuthorDAO mockAuthorDAO;

    @Mock
    Author author;

    @InjectMocks
    AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAuthor() {
        authorService.addAuthor("Test", "Testov", "Testovich");
        verify(mockAuthorDAO).addAuthors(any(Author.class));
    }

    @Test
    public void testTestAddAuthorLongName() {
        authorService.addAuthor("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" +
                        "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT",
                "T", "T");
        verify(mockAuthorDAO, never()).addAuthors(any(Author.class));
    }
}
