/*package org.example;

import java.util.List;
import java.util.stream.IntStream;

public class BookFormatter {
    //private BookDAO dao = new BookDAO();
    private BookDAO dao;

    /*public void printAllBooks() {
        System.out.println("Title, name, surname, patronymic, year:");
        dao.getAllBooks().stream().forEach(b -> System.out.println(b.getCount() + ": " + b.getTitle() +
                ", " + b.getAuthor().getName() + " " + b.getAuthor().getSurname() + " " +
                b.getAuthor().getPatronymic() + ", " + b.getYear()));
        Book.setZeroCount();
    }*/

    /*public void printAllInfoBooks() {
        List<Book> books = dao.getAllInfoBooks();
        for (Book book : books) {
            System.out.println(book.getTitle() + ", " +
                    book.getAuthor().getName() + " " +
                    book.getAuthor().getSurname() + " " +
                    book.getAuthor().getPatronymic() + ", " +
                    book.getYear());
        }
    }*/

    /*public void printAllInfoBooks() {
        List<BookInfo> books = dao.getAllInfoBooks();
        for (BookInfo book : books) {
            System.out.println(book.getTitle() + ", " +
                    book.getAuthorName() + " " +
                    book.getAuthorSurname() + " " +
                    book.getAuthorPatronymic() + ", " +
                    book.getYear());
        }
    }*/

    /*public void printTitleBooks() {
        dao.getTitleBooks().stream().forEach(System.out::println);
    }
}*/