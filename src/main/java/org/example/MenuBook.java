package org.example;

import java.util.Scanner;

public class MenuBook {
    private Scanner in = new Scanner(System.in);
    private BookFormatter formatter = new BookFormatter();
    private String title, authorName, authorSurname, authorPatronymic;
    private int year, id;
    private BookService service = new BookService(new BookDAO());

    public MenuBook() {
        while (true) {
            /*System.out.print("Консольное приложение 'Управление библиотекой'" +
                    "1 - Добавить книгу" +
                    "2 - Показать все книги" +
                    "3 - изменить название книги" +
                    "4 - Удалить книгу" +
                    "5 - Выход" +
                    "Ввод: ");*/
            System.out.print("The console application 'Library Management 2.3.5'\n" +
                    "1 - Add the book\n" +
                    "2 - Show all the books\n" +
                    "3 - change the name of the book\n" +
                    "4 - Delete the book\n" +
                    "5 - Output\n" +
                    "Enter: ");
            String choice = in.nextLine();
            System.out.print("once again: "); in.nextLine();

            switch (choice) {
                case "1":
                    /*System.out.println("Введите информацию о книге:");
                    System.out.print("Название: "); title = in.nextLine();
                    System.out.println("Автор: "); author = in.nextLine();
                    System.out.print("Имя: ");
                    System.out.print("Фамилия: ");
                    System.out.print("Отчество: ");
                    System.out.print("Год: "); year = in.nextInt();*/
                    System.out.println("Enter information about the book: ");
                    System.out.print("Name: "); title = in.nextLine();

                    System.out.println("Author:");
                    System.out.print("Name: "); authorName = in.nextLine();
                    System.out.print("Surname: "); authorSurname = in.nextLine();
                    System.out.print("Patronymic: "); authorPatronymic = in.nextLine();

                    System.out.print("Year: "); year = in.nextInt();

                    service.addBook(title, authorName, authorSurname, authorPatronymic, year);
                    break;
                case "2":
                    formatter.printAllBooks();
                    break;
                case "3":
                    /*System.out.println("Введите ID и название книги для её изменения: ");
                    System.out.print("ID: "); id = in.nextInt();
                    System.out.print("Название: "); title = in.nextLine();*/
                    formatter.printTitleBooks();
                    System.out.println("Enter the ID and name of the book to change it: ");
                    System.out.print("ID: "); id = in.nextInt(); in.nextLine();
                    System.out.print("Name: "); title = in.nextLine();

                    service.updateBook(id, title);
                    break;
                case "4":
                    formatter.printTitleBooks();
                    //System.out.println("Введите ID книги для её удаления: ");
                    System.out.println("Enter ID books to delete it: ");
                    System.out.print("ID: "); id = in.nextInt();

                    service.deleteBook(id);
                    break;
                case "5":
                    System.out.println("You got out of the application");
                    //System.out.println("Вы вышли из приложения");
                    return;
            }
        }
    }
}
