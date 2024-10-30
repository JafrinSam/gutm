package com.library.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Transaction {
    private int userId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private double fine;

    public Transaction(int userId, int bookId, LocalDate borrowDate, LocalDate returnDate, double fine) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }
}
