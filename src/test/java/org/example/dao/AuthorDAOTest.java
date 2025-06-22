package org.example.dao;

import org.example.model.Author;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class AuthorDAOTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("postgres");

    private AuthorDAO authorDAO;

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
            stmt.executeUpdate("CREATE TABLE authors (\n" +
                    "id SERIAL PRIMARY KEY,\n" +
                    "name VARCHAR(64) NOT NULL,\n" +
                    "surname VARCHAR(64) NOT NULL,\n" +
                    "patronymic VARCHAR(64)\n" +
                    ");");
        }

        authorDAO = new AuthorDAO() {
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
            stmt.executeUpdate("DROP TABLE authors");
        }
    }

    @Test
    void testAddAuthor() throws SQLException {
        Author author = new Author("Test1", "Test2", "Test3");
        authorDAO.addAuthor(author);

        try (Connection conn = authorDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT name, surname, patronymic FROM authors WHERE name = ?")) {
            pstmt.setString(1, "Test1");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Test1", rs.getString("name"));
            assertEquals("Test2", rs.getString("surname"));
            assertEquals("Test3", rs.getString("patronymic"));
        }
    }

    @Test
    void testUpdateSurnameAuthor() throws SQLException {
        int id;
        try (Connection conn = authorDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO authors (name, surname, patronymic) VALUES ('Test1', 'Test2', 'Test3')" +
                             " RETURNING id")) {
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            id = rs.getInt("id");
        }

        authorDAO.updateSurnameAuthor(id, "Test22");

        try (Connection conn = authorDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT surname FROM authors WHERE id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Test22", rs.getString("surname"));
        }
    }

    @Test
    void testUpdatePatronymicAuthor() throws SQLException {
        int id;
        try (Connection conn = authorDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO authors (name, surname, patronymic) VALUES ('Test1', 'Test2', 'Test3')" +
                             " RETURNING id")) {
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            id = rs.getInt("id");
        }

        authorDAO.updatePatronymicAuthor(id, "Test33");

        try (Connection conn = authorDAO.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT patronymic FROM authors WHERE id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Test33", rs.getString("patronymic"));
        }
    }

    @Test
    void testGetInfoAuthorOutput() throws SQLException {
        authorDAO.addAuthor(new Author("Test11", "Test12", "Test13"));
        authorDAO.addAuthor(new Author("Test22", "Test22", "Test22"));

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        authorDAO.getInfoAuthor();

        String output = out.toString();
        assertTrue(output.contains("1)"));
        assertTrue(output.contains("Test11"));
        assertTrue(output.contains("Test22"));

        System.setOut(System.out);
    }
}
