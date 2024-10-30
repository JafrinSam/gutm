package com.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.library.model.Book;
import com.library.model.Transaction;
import com.library.util.FileUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final String BOOKS_FILE = "src/main/resources/books.json";
    private static final String TRANSACTIONS_FILE = "src/main/resources/transactions.json";

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) throws Exception {
        List<Book> books = FileUtil.readFromFile(BOOKS_FILE, new TypeReference<List<Book>>() {});
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    @PostMapping("/borrow/{bookId}")
    public String borrowBook(@PathVariable int bookId, @RequestParam int userId) throws Exception {
        List<Book> books = FileUtil.readFromFile(BOOKS_FILE, new TypeReference<List<Book>>() {});
        Book book = books.stream().filter(b -> b.getId() == bookId && b.isAvailable()).findFirst().orElse(null);

        if (book == null) {
            return "Book not available!";
        }

        book.setAvailable(false);
        FileUtil.writeToFile(BOOKS_FILE, books);

        Transaction transaction = new Transaction(userId, bookId, LocalDate.now(), null, 0);
        List<Transaction> transactions = FileUtil.readFromFile(TRANSACTIONS_FILE, new TypeReference<List<Transaction>>() {});
        transactions.add(transaction);
        FileUtil.writeToFile(TRANSACTIONS_FILE, transactions);

        return "Book borrowed successfully!";
    }
}
