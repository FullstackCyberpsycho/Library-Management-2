package org.example.services;

import org.example.dao.AuthorDAO;
import org.example.model.Author;

public class AuthorService {
    public AuthorDAO authorDAO;

    public AuthorService(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public void addAuthor(String name, String surname, String patronymic) {
        if (name.length() > 64 || surname.length() > 64 || patronymic.length() > 64) {
            System.out.println("Error: ФИО слишком длинное");
        } else {
            Author author = new Author(name, surname, patronymic);
            authorDAO.addAuthors(author);
        }
    }

    public void updateAuthor(int id, String newName, String newSurname, String newPatronymic) {
        authorDAO.updateAuthor(id, newName, newSurname, newPatronymic);
    }

    public void updateNameAuthor(int id, String newName) {
        authorDAO.updateNameAuthor(id, newName);
    }

    public void updateSurnameAuthor(int id, String newSurname) {
        authorDAO.updateSurnameAuthor(id, newSurname);
    }

    public void updatePatronymicAuthor(int id, String newPatronymic) {
        authorDAO.updatePatronymicAuthor(id, newPatronymic);
    }

    public void printInfoAuthor() {
        authorDAO.getInfoAuthor();
    }
}
