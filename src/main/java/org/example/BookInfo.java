package org.example;

public class BookInfo {
    private String title;
    private int year;
    private String authorName;
    private String authorSurname;
    private String authorPatronymic;

    public BookInfo(String title, int year, String authorName, String authorSurname, String authorPatronymic) {
        this.title = title;
        this.year = year;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.authorPatronymic = authorPatronymic;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public String getAuthorPatronymic() {
        return authorPatronymic;
    }
}

