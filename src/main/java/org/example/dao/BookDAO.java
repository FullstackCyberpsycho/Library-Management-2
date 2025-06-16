package org.example.dao;

import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final String url = "jdbc:postgresql://localhost:5432/library_management";
    private final String user = "postgres";
    private final String password = "1512BDS7425";
    private int count = 0;

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, year) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.getYear());

            pstmt.executeUpdate();
            System.out.println("Книга добавлена");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllInfoBooks() {
        count = 0;
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Информация о книге пуста!");
            } else {
                System.out.println("Название книги, ФИО, год:");

                while (rs.next()) {
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String patronymic = rs.getString("patronymic");

                    String output = ++count + ") " + title + ", " + name + " " + surname;
                    if (!patronymic.isEmpty()) {
                        output += " " + patronymic;
                    }
                    output += ", " + year;
                    System.out.println(output);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllSortASCNameBook() {
        count = 0;
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id\n" +
                "ORDER BY b.title;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Информация о книге пуста!");
            } else {
                System.out.println("Название книги, ФИО, год:");

                while (rs.next()) {
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String patronymic = rs.getString("patronymic");

                    String output = ++count + ") " + title + ", " + name + " " + surname;
                    if (!patronymic.isEmpty()) {
                        output += " " + patronymic;
                    }
                    output += ", " + year;
                    System.out.println(output);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllSortDESCNameBook() {
        count = 0;
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id\n" +
                "ORDER BY b.title DESC;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Информация о книге пуста!");
            } else {
                System.out.println("Название книги, ФИО, год:");

                while (rs.next()) {
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String patronymic = rs.getString("patronymic");

                    String output = ++count + ") " + title + ", " + name + " " + surname;
                    if (!patronymic.isEmpty()) {
                        output += " " + patronymic;
                    }
                    output += ", " + year;
                    System.out.println(output);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllSortASCNameAuthor() {
        count = 0;
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id\n" +
                "ORDER BY a.name;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Информация о книге пуста!");
            } else {
                System.out.println("Название книги, ФИО, год:");

                while (rs.next()) {
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String patronymic = rs.getString("patronymic");

                    String output = ++count + ") " + title + ", " + name + " " + surname;
                    if (!patronymic.isEmpty()) {
                        output += " " + patronymic;
                    }
                    output += ", " + year;
                    System.out.println(output);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllSortDESCNameAuthor() {
        count = 0;
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id\n" +
                "ORDER BY a.name DESC;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Информация о книге пуста!");
            } else {
                System.out.println("Название книги, ФИО, год:");

                while (rs.next()) {
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String patronymic = rs.getString("patronymic");

                    String output = ++count + ") " + title + ", " + name + " " + surname;
                    if (!patronymic.isEmpty()) {
                        output += " " + patronymic;
                    }
                    output += ", " + year;
                    System.out.println(output);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllSortASCYearBook() {
        count = 0;
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id\n" +
                "ORDER BY b.year;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Информация о книге пуста!");
            } else {
                System.out.println("Название книги, ФИО, год:");

                while (rs.next()) {
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String patronymic = rs.getString("patronymic");

                    String output = ++count + ") " + title + ", " + name + " " + surname;
                    if (!patronymic.isEmpty()) {
                        output += " " + patronymic;
                    }
                    output += ", " + year;
                    System.out.println(output);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllSortDESCYearBook() {
        count = 0;
        String sql = "SELECT b.title, b.year, a.name, a.surname, a.patronymic FROM books b\n" +
                "JOIN authors a ON b.author_id = a.id\n" +
                "ORDER BY b.year DESC;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Информация о книге пуста!");
            } else {
                System.out.println("Название книги, ФИО, год:");

                while (rs.next()) {
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String patronymic = rs.getString("patronymic");

                    String output = ++count + ") " + title + ", " + name + " " + surname;
                    if (!patronymic.isEmpty()) {
                        output += " " + patronymic;
                    }
                    output += ", " + year;
                    System.out.println(output);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getTitleBooks() {
        count = 0;
        String sql = "SELECT id, title FROM books";
        List<String> titles = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Номер, id, название книги");
            while (rs.next()) {
                String title = rs.getString("title");
                int id = rs.getInt("id");
                titles.add(id + ": " + title);

                System.out.println(++count + ") " + id + ":" + title);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getTitleAndYearBooks() {
        count = 0;
        String sql = "SELECT id, title, year FROM books";
        List<String> titles = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Номер, id, название, год книги");
            while (rs.next()) {
                String title = rs.getString("title");
                int id = rs.getInt("id");
                int year = rs.getInt("year");

                titles.add(id + ": " + title);

                System.out.println(++count + ") " + id + ":" + title + " - " + year);
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
            System.out.println("Название книги изменено");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(int id, String newTitle, String newYear) {
        String sql = "UPDATE books SET title = ?, year = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newTitle);
            pstmt.setInt(2, Integer.parseInt(newYear));
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
            System.out.println("Название и год книги изменены");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String sql1 = "DELETE FROM books WHERE id = ?";
        String sql2 = "DELETE FROM authors WHERE id = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                 PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {

                pstmt1.setInt(1, id);
                pstmt1.executeUpdate();
                System.out.print("Книга и ");

                pstmt2.setInt(1, id);
                pstmt2.executeUpdate();
                System.out.println("автор удалены");

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllBook() {
        String sql1 = "DELETE FROM books;";
        String sql2 = "DELETE FROM authors;";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                 PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {

                pstmt1.executeUpdate();
                System.out.print("Книги и ");

                pstmt2.executeUpdate();
                System.out.println("авторы удалены");

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}