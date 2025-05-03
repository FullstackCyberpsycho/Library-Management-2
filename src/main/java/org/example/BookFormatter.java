package org.example;

public class BookFormatter {
    private BookDAO dao = new BookDAO();

    public void printAllBooks() {
        System.out.println("Title, name, surname, patronymic, year:");
        dao.getAllBooks().stream().forEach(b -> System.out.println(b.getCount() + ": " + b.getTitle() +
                ", " + b.getAuthor().getName() + " " + b.getAuthor().getSurname() + " " +
                b.getAuthor().getPatronymic() + ", " + b.getYear()));
        Book.setZeroCount();
    }

    public void printTitleBooks() {
        dao.getTitleBooks().stream().forEach(System.out::println);
    }
}