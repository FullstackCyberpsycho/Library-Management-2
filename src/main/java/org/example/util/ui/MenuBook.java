package org.example.util.ui;

import org.example.dao.AuthorDAO;
import org.example.dao.BookDAO;
import org.example.services.AuthorService;
import org.example.services.BookService;

import java.util.Scanner;

public class MenuBook {
    private Scanner in = new Scanner(System.in);
    private String title, name, surname, patronymic, year, id, choice;
    private BookService bookService = new BookService(new BookDAO());
    private AuthorService authorService = new AuthorService(new AuthorDAO());

    public MenuBook() {
        while (true) {
            System.out.print("Консольное приложение 'Library Management 2.8.15'\n" +
                    "1 - Добавить книгу\n" +
                    "2 - Показать все книги\n" +
                    "3 - Изменить книгу\n" +
                    "4 - Удалить книгу\n" +
                    "5 - Выход\n" +
                    "Ввод: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Введите информацию о книге:");
                    System.out.print("Название: "); title = in.nextLine();
                    System.out.print("Год: "); year = in.nextLine();

                    System.out.println("Введите информацию о авторе книги:");
                    System.out.print("Имя: "); name = in.nextLine();
                    System.out.print("Фамилия: "); surname = in.nextLine();
                    System.out.print("Отчество: "); patronymic = in.nextLine();

                    authorService.addAuthor(name, surname, patronymic);
                    bookService.addBook(title, Integer.parseInt(year));
                    break;
                case "2":
                    bookService.printAllInfoBook();

                    System.out.print("1 - Сортировать\n"+
                            "Ввод: ");
                    choice = in.nextLine();

                    if (choice.equals("1")) {
                        System.out.print("1 - По названию книги\n" +
                                "2 - По имени автора\n" +
                                "3 - По году книги\n" +
                                "Ввод: ");
                        choice = in.nextLine();

                        switch (choice) {
                            case "1":
                                System.out.print("1 - По возрастанию\n" +
                                        "2 - По убыванию\n" +
                                        "Ввод: ");
                                choice = in.nextLine();
                                if (choice.equals("1")) {
                                    bookService.printAllSortASCNameBook();

                                    System.out.print("Нажмите 'Enter' чтобы продолжить ");
                                    Scanner in = new Scanner(System.in);
                                    in.nextLine();
                                } else if (choice.equals("2")) {
                                    bookService.printAllSortDESCNameBook();

                                    System.out.print("Нажмите 'Enter' чтобы продолжить ");
                                    Scanner in = new Scanner(System.in);
                                    in.nextLine();
                                }
                                break;
                            case "2":
                                System.out.print("1 - По возрастанию\n" +
                                        "2 - По убыванию\n" +
                                        "Ввод: ");
                                choice = in.nextLine();
                                if (choice.equals("1")) {
                                    bookService.printAllSortASCNameAuthor();

                                    System.out.print("Нажмите 'Enter' чтобы продолжить ");
                                    Scanner in = new Scanner(System.in);
                                    in.nextLine();
                                } else if (choice.equals("2")) {
                                    bookService.printAllSortDESCNameAuthor();

                                    System.out.print("Нажмите 'Enter' чтобы продолжить ");
                                    Scanner in = new Scanner(System.in);
                                    in.nextLine();
                                }
                                break;
                            case "3":
                                System.out.print("1 - По возрастанию\n" +
                                        "2 - По убыванию\n" +
                                        "Ввод: ");
                                choice = in.nextLine();
                                if (choice.equals("1")) {
                                    bookService.printAllSortASCYearBook();

                                    System.out.print("Нажмите 'Enter' чтобы продолжить ");
                                    Scanner in = new Scanner(System.in);
                                    in.nextLine();
                                } else if (choice.equals("2")) {
                                    bookService.printAllSortDESCYearBook();

                                    System.out.print("Нажмите 'Enter' чтобы продолжить ");
                                    Scanner in = new Scanner(System.in);
                                    in.nextLine();
                                }
                                break;
                        }
                    }

                    break;
                case "3":
                     System.out.print("1 - Изменить название книги\n" +
                            "2 - Изменить название и год книги\n" +
                            "3 - Изменить автора книги\n" +
                            "Ввод: ");
                    choice = in.nextLine();

                    switch (choice) {
                        case "1":
                            bookService.printTitleBooks();
                            System.out.println("Введите ID и название книги для её изменения: ");
                            System.out.print("ID: "); id = in.nextLine();
                            System.out.print("Название: "); title = in.nextLine();

                            bookService.updateBook(Integer.parseInt(id), title);
                            break;
                        case "2":
                            bookService.printgetTitleAndYearBooks();
                            System.out.println("Введите ID, название и год книги: ");
                            System.out.print("ID: "); id = in.nextLine();
                            System.out.print("Название: "); title = in.nextLine();
                            System.out.print("Год: "); year = in.nextLine();

                            bookService.updateBook(Integer.parseInt(id), title, year);
                            break;
                        case "3":
                            System.out.print("1 - Изменить ФИО\n" +
                                    "2 - Изменить имя\n" +
                                    "3 - Изиенить фамилию\n" +
                                    "4 - Изменить отчество\n" +
                                    "Ввод: ");
                            choice = in.nextLine();

                            switch (choice) {
                                case "1":
                                   authorService.printInfoAuthor();
                                    System.out.println("Введите ID и информацию о авторе книги:");
                                    System.out.print("ID: "); id = in.nextLine();
                                    System.out.print("Имя: "); name = in.nextLine();
                                    System.out.print("Фамилия: "); surname = in.nextLine();
                                    System.out.print("Отчество: "); patronymic = in.nextLine();

                                    authorService.updateAuthor(Integer.parseInt(id), name, surname, patronymic);
                                    break;
                                case "2":
                                    authorService.printInfoAuthor();
                                    System.out.println("Введите ID и имя автора");
                                    System.out.print("ID: "); id = in.nextLine();
                                    System.out.print("Имя: "); name = in.nextLine();

                                    authorService.updateNameAuthor(Integer.parseInt(id), name);
                                    break;
                                case "3":
                                    authorService.printInfoAuthor();
                                    System.out.println("Введите ID и фамилию автора");
                                    System.out.print("ID: "); id = in.nextLine();
                                    System.out.print("Фамилия: "); surname = in.nextLine();

                                    authorService.updateSurnameAuthor(Integer.parseInt(id), surname);
                                    break;
                                case "4":
                                    authorService.printInfoAuthor();
                                    System.out.println("Введите ID и отчество автора");
                                    System.out.print("ID: "); id = in.nextLine();
                                    System.out.print("Отчество: "); patronymic = in.nextLine();

                                    authorService.updatePatronymicAuthor(Integer.parseInt(id), patronymic);
                                    break;
                            }
                            break;
                    }
                    break;
                case "4":
                    System.out.print("1 - Удалить определенную книгу\n" +
                            "2 - Удалить все книги\n" +
                            "Ввод: ");
                    choice = in.nextLine();

                    if (choice.equals("1")) {
                        bookService.printTitleBooks();
                        System.out.println("Введите ID книги для её удаления: ");
                        System.out.print("ID: "); id = in.nextLine();

                        bookService.deleteBook(Integer.parseInt(id));
                    } else if (choice.equals("2")) {
                        bookService.deleteAllBook();
                    }
                    break;
                case "5":
                    System.out.println("Вы вышли из приложения");
                    return;
            }
        }
    }
}