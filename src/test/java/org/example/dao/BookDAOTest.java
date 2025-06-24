package org.example.dao;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;

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

            stmt.execute("CREATE TABLE books (" +
                    "id SERIAL PRIMARY KEY," +
                    "title VARCHAR(100)," +
                    "year INT," +
                    "author_id INT REFERENCES authors(id)" +
                    ")");
        }
    }


    @AfterAll
    public static void stop() {
        postgres.stop();
    }
}
