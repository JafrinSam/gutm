package com.library;

import com.library.gui.MainGUI;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitalLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DigitalLibraryApplication.class, args);
        // Launch the GUI
        Application.launch(MainGUI.class, args);
    }
}
