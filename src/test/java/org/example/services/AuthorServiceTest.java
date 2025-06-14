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

    @Test
    public void testUpdateAuthor() {
        authorService.updateAuthor(1, "Tn", "Ts", "Tp");
        verify(mockAuthorDAO).updateAuthor(1, "Tn", "Ts", "Tp");
    }

    @Test
    public void testUpdateNameAuthor() {
        authorService.updateNameAuthor(1, "name");
        verify(mockAuthorDAO).updateNameAuthor(1, "name");
    }

    @Test
    public void testUpdateSurnameAuthor() {
        authorService.updateSurnameAuthor(1, "surname");
        verify(mockAuthorDAO).updateSurnameAuthor(1, "surname");
    }

    @Test
    public void testUpdatePatronymicAuthor() {
        authorService.updatePatronymicAuthor(1, "patronymic");
        verify(mockAuthorDAO).updatePatronymicAuthor(1, "patronymic");
    }

}
