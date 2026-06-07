package main;

import model.Book;
import model.Reader;
import service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService service = new LibraryService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= БИБЛИОТЕКА =========");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Добавить читателя");
            System.out.println("3. Выдать книгу");
            System.out.println("4. Принять возврат");
            System.out.println("5. Книги у читателя");
            System.out.println("6. Найти книгу");
            System.out.println("7. Все свободные книги");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.print("ID книги: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Название: ");
                    String title = scanner.nextLine();
                    System.out.print("Автор: ");
                    String author = scanner.nextLine();
                    System.out.print("Год: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    service.addBook(new Book(bookId, title, author, year));
                    break;

                case "2":
                    System.out.print("ID читателя: ");
                    int readerId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Телефон: ");
                    String phone = scanner.nextLine();
                    service.addReader(new Reader(readerId, name, phone));
                    break;

                case "3":
                    System.out.print("ID читателя: ");
                    int rId = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID книги: ");
                    int bId = Integer.parseInt(scanner.nextLine());
                    service.borrowBook(rId, bId);
                    break;

                case "4":
                    System.out.print("ID читателя: ");
                    int rId2 = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID книги: ");
                    int bId2 = Integer.parseInt(scanner.nextLine());
                    service.returnBook(rId2, bId2);
                    break;

                case "5":
                    System.out.print("ID читателя: ");
                    int rId3 = Integer.parseInt(scanner.nextLine());
                    service.getReaderBooks(rId3);
                    break;

                case "6":
                    System.out.print("Введите название или автора: ");
                    String query = scanner.nextLine();
                    service.searchBook(query);
                    break;

                case "7":
                    service.getAvailableBooks();
                    break;

                case "0":
                    System.out.println("До свидания!");
                    return;

                default:
                    System.out.println("❌ Неверный пункт меню!");
            }
        }
    }
}
