package org.example.dao;

import org.example.model.Book;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookDAOTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("postgres");

    private BookDAO bookDAO;

    @BeforeAll
    static void startContainer() {
        postgres.start();
    }

    @AfterAll
    static void stopContainer() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() throws SQLException {
        try (Connection conn = DriverManager.getConnection(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword());
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE books (\n" +
                    "id SERIAL PRIMARY KEY,\n" +
                    "title VARCHAR(128) NOT NULL,\n" +
                    "year INTEGER NOT NULL,\n" +
                    "author_id SERIAL REFERENCES authors(id) \n" +
                    ");");
        }

        bookDAO = new BookDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                return DriverManager.getConnection(
                        postgres.getJdbcUrl(),
                        postgres.getUsername(),
                        postgres.getPassword());
            }
        };
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection conn = DriverManager.getConnection(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword());
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE books");
        }
    }

    @Test
    void testAddAdd() throws SQLException {
        Book book = new Book("Test", 1234);
        bookDAO.addBook(book);

        try (Connection conn = bookDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT title, year FROM books WHERE title = ?")) {
            pstmt.setString(1, "Test");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
        }
    }

    /*@Test
    void testGetAllInfoBooks() {
        Book book1 = new Book("Test1", 1234);
        Book book2 = new Book("Test2", 4321);
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        bookDAO.getAllInfoBooks();

        String output = out.toString();
        assertTrue(output.contains("1)"));
        assertTrue(output.contains("Test1"));
        assertTrue(output.contains("Test2"));

        System.setOut(System.out);
    }

    @Test
    void testGetTitleBooks() {
        Book book1 = new Book("Test1", 1234);
        bookDAO.addBook(book1);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        bookDAO.getTitleBooks();

        String output = out.toString();
        assertTrue(output.contains("1)"));
        assertTrue(output.contains("Test1"));

        System.setOut(System.out);
    }*/

    @Test
    void testUpdateBook() throws SQLException {
        int id;
        try (Connection conn = bookDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO books (title, year) VALUES ('Test', 1234) RETURNING id")) {
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            id = rs.getInt("id");
        }

        bookDAO.updateBook(id, "Test2");

        try (Connection conn = bookDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT title FROM books WHERE id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Test2", rs.getString("title"));
        }
    }
}
