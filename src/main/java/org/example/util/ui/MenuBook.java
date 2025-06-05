package org.example.util.ui;

import org.example.dao.AuthorDAO;
import org.example.dao.BookDAO;
import org.example.services.BookService;

import java.util.Scanner;

public class MenuBook {
    private Scanner in = new Scanner(System.in);
    private String title, name, surname, patronymic, year, id, choice;
    private BookService service = new BookService(new BookDAO(), new AuthorDAO());

    public MenuBook() {
        while (true) {
            System.out.print("Консольное приложение 'Library Management 2.5.12'\n" +
                    "1 - Добавить книгу\n" +
                    "2 - Показать все книги\n" +
                    "3 - изменить название книги\n" +
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

                    service.addAuthor(name, surname, patronymic);
                    service.addBook(title, Integer.parseInt(year));
                    break;
                case "2":
                    service.printAllInfoBook();
                    break;
                case "3":
                    service.printTitleBooks();
                    System.out.println("Введите ID и название книги для её изменения: ");
                    System.out.print("ID: "); id = in.nextLine();
                    System.out.print("Название: "); title = in.nextLine();

                    service.updateBook(Integer.parseInt(id), title);
                    break;
                case "4":
                    System.out.print("1 - Удалить определенную книгу\n" +
                            "2 - Удалить все книги\n" +
                            "Ввод: ");
                    choice = in.nextLine();

                    if (choice.equals("1")) {
                        service.printTitleBooks();
                        System.out.println("Введите ID книги для её удаления: ");
                        System.out.print("ID: "); id = in.nextLine();

                        service.deleteBook(Integer.parseInt(id));
                    } else if (choice.equals("2")) {
                        service.deleteAllBook();
                    }
                    break;
                case "5":
                    System.out.println("Вы вышли из приложения");
                    return;
            }
        }
    }
}