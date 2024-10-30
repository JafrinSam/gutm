package com.library.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.library.model.Book;
import com.library.util.FileUtil;

import java.util.List;

public class MainGUI extends Application {
    private static final String BOOKS_FILE = "src/main/resources/books.json";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Digital Library Management");

        VBox layout = new VBox(10);
        layout.setPrefWidth(400);

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("Enter book title...");
        Button searchButton = new Button("Search");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        searchButton.setOnAction(e -> {
            String title = searchField.getText();
            List<Book> books = searchBooks(title);
            StringBuilder results = new StringBuilder();

            for (Book book : books) {
                results.append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
            }

            resultArea.setText(results.toString());
        });

        layout.getChildren().addAll(searchField, searchButton, resultArea);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<Book> searchBooks(String title) {
        try {
            List<Book> books = FileUtil.readFromFile(BOOKS_FILE, new TypeReference<List<Book>>() {});
            return books.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
