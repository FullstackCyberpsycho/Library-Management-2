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
}
