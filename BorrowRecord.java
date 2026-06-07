package model;

public class BorrowRecord {
    private int bookId;
    private int readerId;
    private String borrowDate;
    private String returnDate;

    public BorrowRecord(int bookId, int readerId, String borrowDate) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    public int getBookId() { return bookId; }
    public int getReaderId() { return readerId; }
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    public String toString() {
        return "Книга ID: " + bookId + " | Читатель ID: " + readerId +
                " | Взята: " + borrowDate + " | Возврат: " + (returnDate != null ? returnDate : "ещё не вернул");
    }
}