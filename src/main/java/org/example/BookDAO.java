package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final String url = "jdbc:postgresql://localhost:5432/library_management";
    private final String user = "postgres";
    private final String password = "1512BDS7425";

    public void addBook(Book book) {
        AuthorId authorId = new AuthorId();
        String sql = "INSERT INTO books (title, year, author_id) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.getYear());
            pstmt.setInt(3, authorId.getId());
            pstmt.executeUpdate();
            //System.out.println("Книга добавлена");
            System.out.println("The book is added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllInfoBooks() {
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("title, name, surname, patronymic, год");
            while (rs.next()) {
                String title = rs.getString("title");
                int year = rs.getInt("Year");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");


                System.out.println(title + ", " + name + " " + surname + " " + patronymic + ", " + year);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getTitleBooks() {
        int count = 0;
        String sql = "SELECT id, title FROM books";
        List<String> titles = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String title = rs.getString("title");
                int id = rs.getInt("id");
                titles.add(id + ": " + title);

                System.out.println(id + ": " + title);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(int id, String newTitle) {
        String sql = "UPDATE books SET title = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newTitle);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            //System.out.println("Название книги изменено");
            System.out.println("The name of the book is changed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            //System.out.println("Книга удалена");
            System.out.println("The book is deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}