package org.example;

public class Book {
    private int id;
    private String title;
    private int year;
    private static int count = 0;
    private static int author_id = 0;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int getCount() {
        return ++count;
    }

    public static void setZeroCount() {
        count = 0;
    }


}
