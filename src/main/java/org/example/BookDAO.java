package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final String url = "jdbc:postgresql://localhost:5432/library_management";
    private final String user = "postgres";
    private final String password = "1512BDS7425";

    public void addBook(Book book) {
        String sql = "INSERT INTO books (Title, AuthorName, AuthorSurname, AuthorPatronymic, Year) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor().getName());
            pstmt.setString(3, book.getAuthor().getSurname());
            pstmt.setString(4, book.getAuthor().getPatronymic());
            pstmt.setInt(5, book.getYear());
            pstmt.executeUpdate();
            //System.out.println("Книга добавлена");
            System.out.println("The book is added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT Id, Title, AuthorName, AuthorSurname, AuthorPatronymic, Year FROM books";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String authorName = rs.getString("AuthorName");
                String authorSurname = rs.getString("AuthorSurname");
                String authorPatronymic = rs.getString("AuthorPatronymic");
                int year = rs.getInt("Year");

                Author author = new Author(authorName, authorSurname, authorPatronymic);
                Book book = new Book(id, title, author, year);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    public List<String> getTitleBooks() {
        int count = 0;
        String sql = "SELECT Id, Title FROM books";
        List<String> titles = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String title = rs.getString("Title");
                int id = rs.getInt("Id");
                titles.add(id + ": " + title);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titles;
    }

    public void updateBook(int id, String newTitle) {
        String sql = "UPDATE books SET Title = ? WHERE Id = ?";
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
        String sql = "DELETE FROM books WHERE Id = ?";
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

