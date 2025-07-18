package org.example.model;

public class Book {
    private String title;
    private int year;
    private Author author;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }
}
