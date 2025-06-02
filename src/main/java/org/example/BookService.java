package org.example;


public class BookService {
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;


    public BookService(BookDAO dao, AuthorDAO authorDAO) {
        this.bookDAO = dao;
        this.authorDAO = authorDAO;
    }

    public void printAllInfoBook() {
        bookDAO.getAllInfoBooks();
    }

    public void printTitleBooks() {
        bookDAO.getTitleBooks();
    }

    public void addAuthor(String name, String surname, String patronymic) {
        if (name.length() > 64 || surname.length() > 64 || patronymic.length() > 64) {
            System.out.println("Error: length...");
        } else {
            Author author = new Author(name, surname, patronymic);
            authorDAO.addAuthors(author);
        }
    }

    public void addBook(String title, int year) {
        if (title.length() > 150) {
            System.out.println("Error: length...");
        } else {
           AuthorId authorId = new AuthorId();

            authorId.setId();
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
}
