package model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String toString() {
        return "ID: " + id + " | " + title + " — " + author + " (" + year + ") | Доступна: " + available;
    }
}