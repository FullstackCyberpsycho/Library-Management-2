package org.example.dao;

import org.example.model.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDAO {
    private final String url = "jdbc:postgresql://localhost:5432/library_management";
    private final String user = "postgres";
    private final String password = "1512BDS7425";

    public void addAuthors(Author author) {
        String sql = "INSERT INTO authors (name, surname, patronymic) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.setString(2, author.getSurname());
            pstmt.setString(3, author.getPatronymic());
            pstmt.executeUpdate();
            System.out.println("Автор добавлен");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
