package org.example.dao;

import org.example.model.Book;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class BookDAOTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    private static BookDAO bookDAO;

    @BeforeAll
    public static void setupDatabase() throws SQLException {
        postgres.start();

        bookDAO = new BookDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                return DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
            }
        };

        try (Connection conn = bookDAO.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE authors (\n" +
                    "id SERIAL PRIMARY KEY,\n" +
                    "name VARCHAR(64) NOT NULL,\n" +
                    "surname VARCHAR(64) NOT NULL,\n" +
                    "patronymic VARCHAR(64)\n" +
                    ");");

            stmt.execute("CREATE TABLE books (\n" +
                    "id SERIAL PRIMARY KEY,\n" +
                    "title VARCHAR(128) NOT NULL,\n" +
                    "year INTEGER NOT NULL\n" +
                    //"author_id SERIAL REFERENCES authors(id) \n" +
                    ");");
        }
    }

    @Test
    void testAddBook() throws SQLException {
        Book book = new Book("Test", 1234);
        bookDAO.addBook(book);

        try (Connection conn = bookDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT title, year FROM books WHERE title = ?")) {
            pstmt.setString(1, "Test");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Test", rs.getString("title"));
            //assertEquals(1234, rs.getString("year"));
        }
    }

    @AfterAll
    public static void stop() {
        postgres.stop();
    }
}
