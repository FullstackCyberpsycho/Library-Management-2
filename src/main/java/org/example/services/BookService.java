package org.example.services;

import org.example.dao.AuthorDAO;
import org.example.dao.BookDAO;
import org.example.model.Book;

public class BookService {
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;

    public BookService(BookDAO dao) {
        this.bookDAO = dao;
    }

    public void printAllInfoBook() {
        bookDAO.getAllInfoBooks();
    }

    public void printTitleBooks() {
        bookDAO.getTitleBooks();
    }

    public void printAllSortASCNameBook() {
        bookDAO.getAllSortASCNameBook();
    }

    public void printAllSortDESCNameBook() {
        bookDAO.getAllSortDESCNameBook();
    }

    public void printAllSortASCNameAuthor() {
        bookDAO.getAllSortASCNameAuthor();
    }

    public void printAllSortDESCNameAuthor() {
        bookDAO.getAllSortDESCNameAuthor();
    }

    public void printAllSortASCYearBook() {
        bookDAO.getAllSortASCYearBook();
    }

    public void printAllSortDESCYearBook() {
        bookDAO.getAllSortDESCYearBook();
    }

    public void addBook(String title, int year) {
        if (title.length() > 150) {
            System.out.println("Error: Название книги слишком длинное");
        } else {
            Book book = new Book(title, year);
            bookDAO.addBook(book);
        }
    }

    public void updateBook(int id, String title) {
        bookDAO.updateBook(id, title);
    }

    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    public void deleteAllBook() {
        bookDAO.deleteAllBook();
    }
}
