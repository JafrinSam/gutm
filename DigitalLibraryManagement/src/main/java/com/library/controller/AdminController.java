package com.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.library.model.Book;
import com.library.util.FileUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final String BOOKS_FILE = "src/main/resources/books.json";

    @GetMapping("/books")
    public List<Book> getAllBooks() throws Exception {
        return FileUtil.readFromFile(BOOKS_FILE, new TypeReference<List<Book>>() {});
    }

    @PostMapping("/books")
    public String addBook(@RequestBody Book book) throws Exception {
        List<Book> books = getAllBooks();
        book.setId(books.size() + 1);  // Auto-increment ID
        books.add(book);
        FileUtil.writeToFile(BOOKS_FILE, books);
        return "Book added successfully!";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id) throws Exception {
        List<Book> books = getAllBooks();
        books.removeIf(book -> book.getId() == id);
        FileUtil.writeToFile(BOOKS_FILE, books);
        return "Book deleted successfully!";
    }
}
