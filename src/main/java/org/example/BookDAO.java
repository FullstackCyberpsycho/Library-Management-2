package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final String url = "jdbc:postgresql://localhost:5432/library_management";
    private final String user = "postgres";
    private final String password = "1512BDS7425";

    /*public void addBook(Book book) {
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
    }*/

    public void addAuthors(Author author) {
        String sql = "INSERT INTO authors (name, surname, patronymic) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.setString(2, author.getSurname());
            pstmt.setString(3, author.getPatronymic());
            pstmt.executeUpdate();
            //System.out.println("Книга добавлена");
            System.out.println("The author is added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, year) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.getYear());
            pstmt.executeUpdate();
            //System.out.println("Книга добавлена");
            System.out.println("The book is added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public List<Book> getAllBooks() {
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
    }*/

    public /*List<Book>*/ void getAllInfoBooks() {
        //List<Book> books = new ArrayList<>();
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String title = rs.getString("title");
                int year = rs.getInt("Year");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");

                //Author author = new Author(name, surname, patronymic);
                //Book book = new Book(title, year/*, author*/);
                //books.add(book);
                //System.out.println("fdsf");
                System.out.println(title + ", " + name + " " + surname + " " + patronymic + ", " + year);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return books;
    }

    /*public List<BookInfo> getAllInfoBooks() {
        List<BookInfo> books = new ArrayList<>();
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic " +
                "FROM books b JOIN authors a ON b.author_id = a.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");

                books.add(new BookInfo(title, year, name, surname, patronymic));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }*/

    public List<String> getTitleBooks() {
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titles;
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