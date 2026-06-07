package service;

import model.Book;
import model.BorrowRecord;
import model.Reader;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Reader> readers = new ArrayList<>();
    private ArrayList<BorrowRecord> records = new ArrayList<>();
    private HashMap<Integer, Book> bookMap = new HashMap<>();
    private HashMap<Integer, Reader> readerMap = new HashMap<>();

    // Добавить книгу
    public void addBook(Book book) {
        books.add(book);
        bookMap.put(book.getId(), book);
        System.out.println("✅ Книга добавлена: " + book.getTitle());
    }

    // Добавить читателя
    public void addReader(Reader reader) {
        readers.add(reader);
        readerMap.put(reader.getId(), reader);
        System.out.println("✅ Читатель добавлен: " + reader.getName());
    }

    // Выдать книгу
    public void borrowBook(int readerId, int bookId) {
        if (!bookMap.containsKey(bookId)) {
            System.out.println("❌ Книга с ID " + bookId + " не найдена!");
            return;
        }
        if (!readerMap.containsKey(readerId)) {
            System.out.println("❌ Читатель с ID " + readerId + " не найден!");
            return;
        }
        Book book = bookMap.get(bookId);
        if (!book.isAvailable()) {
            System.out.println("❌ Книга уже выдана другому читателю!");
            return;
        }
        book.setAvailable(false);
        records.add(new BorrowRecord(bookId, readerId, LocalDate.now().toString()));
        System.out.println("✅ Книга \"" + book.getTitle() + "\" выдана читателю " + readerMap.get(readerId).getName());
    }

    // Принять возврат
    public void returnBook(int readerId, int bookId) {
        for (BorrowRecord record : records) {
            if (record.getBookId() == bookId && record.getReaderId() == readerId && record.getReturnDate() == null) {
                record.setReturnDate(LocalDate.now().toString());
                bookMap.get(bookId).setAvailable(true);
                System.out.println("✅ Книга возвращена!");
                return;
            }
        }
        System.out.println("❌ Запись о выдаче не найдена!");
    }

    // Книги у читателя
    public void getReaderBooks(int readerId) {
        if (!readerMap.containsKey(readerId)) {
            System.out.println("❌ Читатель не найден!");
            return;
        }
        System.out.println("📚 Книги у читателя " + readerMap.get(readerId).getName() + ":");
        boolean found = false;
        for (BorrowRecord record : records) {
            if (record.getReaderId() == readerId && record.getReturnDate() == null) {
                System.out.println("  " + bookMap.get(record.getBookId()));
                found = true;
            }
        }
        if (!found) System.out.println("  Нет книг на руках.");
    }

    // Поиск книги по названию или автору
    public void searchBook(String query) {
        System.out.println("🔍 Результаты поиска \"" + query + "\":");
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                System.out.println("  " + book);
                found = true;
            }
        }
        if (!found) System.out.println("  Ничего не найдено.");
    }

    // Все свободные книги
    public void getAvailableBooks() {
        System.out.println("📖 Свободные книги:");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("  " + book);
                found = true;
            }
        }
        if (!found) System.out.println("  Все книги выданы.");
    }

    // Топ-3 читателя
    public void getTopReaders() {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (BorrowRecord record : records) {
            counts.put(record.getReaderId(), counts.getOrDefault(record.getReaderId(), 0) + 1);
        }
        System.out.println("🏆 Топ-3 читателя:");
        counts.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(3)
                .forEach(e -> {
                    Reader r = readerMap.get(e.getKey());
                    System.out.println("  " + r.getName() + " — " + e.getValue() + " книг");
                });
    }

    // Читатели с книгами на руках
    public void getReadersWithBooks() {
        System.out.println("👥 Читатели с книгами на руках:");
        Set<Integer> ids = new HashSet<>();
        for (BorrowRecord record : records) {
            if (record.getReturnDate() == null) ids.add(record.getReaderId());
        }
        if (ids.isEmpty()) {
            System.out.println("  Никто не держит книги.");
            return;
        }
        for (int id : ids) System.out.println("  " + readerMap.get(id));
    }
}
