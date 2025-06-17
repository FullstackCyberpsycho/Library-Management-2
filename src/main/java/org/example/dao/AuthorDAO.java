package org.example.dao;

import org.example.model.Author;

import java.sql.*;

public class AuthorDAO {
    private final String url =
    private final String user =
    private final String password =
    private Connection connection;

    public AuthorDAO(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthorDAO() {}

    public void addAuthor(Author author) {
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

    public Author findAuthor(String name, String surname, String patronymic) {
        String sql = "SELECT name, surname, patronymic FROM authors WHERE name = ? AND surname = ? AND patronymic = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, patronymic);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Author(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("patronymic")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void getInfoAuthor() {
        int count = 0;
        String sql = "SELECT * FROM authors;";
        //List<String> titles = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Номер, id и ФИО авторов");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");

                //titles.add(id + ": " + title);

                System.out.println(++count + ") " + id + ":" + name + " " + surname + " " + patronymic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAuthor(int id, String newName, String newSurname, String newPatronymic) {
        String sql = "UPDATE authors SET name = ?, surname = ?, patronymic = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newSurname);
            pstmt.setString(3, newPatronymic);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();

            System.out.println("ФИО автора изменено");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNameAuthor(int id, String newName) {
        String sql = "UPDATE authors SET name = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

            System.out.println("Имя автора изменено");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSurnameAuthor(int id, String newSurname) {
        String sql = "UPDATE authors SET surname = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newSurname);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

            System.out.println("Фамилия автора изменено");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatronymicAuthor(int id, String newPatronymic) {
        String sql = "UPDATE authors SET patronymic = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPatronymic);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

            System.out.println("Отчество автора изменено");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
