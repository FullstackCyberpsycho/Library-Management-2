package org.example;

public class BookService {
    //private BookDAO dao = new BookDAO();
    private BookDAO dao;

    public BookService(BookDAO dao) {
        this.dao = dao;
    }

    public void printAllInfoBook() {
        dao.getAllInfoBooks();
    }


    /*public void addBook(String title, String authorName, String authorSurname, String authorPatronymic, int year) {
        if (title.length() > 128) {
            System.out.println("Название должно быть меньше 128 символов");
        } else if (authorName.length() > 32 || authorSurname.length() > 32 || authorPatronymic.length() > 32) {
            System.out.println("ФИО... должно быть меньше 32 символов");
        } else {
            Author author = new Author(authorName, authorSurname, authorPatronymic);
            Book book = new Book(title, author, year);
            dao.addBook(book);
        }
    }*/

    public void addAuthor(String name, String surname, String patronymic) {
        if (name.length() > 64 || surname.length() > 64 || patronymic.length() > 64) {
            System.out.println("Error: length...");
        } else {
            Author author = new Author(name, surname, patronymic);
            dao.addAuthors(author);
        }
    }

    public void addBook(String title, int year/*, Author author*/) {
        if (title.length() > 150) {
            System.out.println("Error: length...");
        } else {
            Book book = new Book(title, year/*, author*/);
            dao.addBook(book);
        }
    }

    public void updateBook(int id, String title) {
        dao.updateBook(id, title);
    }

    public void deleteBook(int id) {
        dao.deleteBook(id);
    }
}
