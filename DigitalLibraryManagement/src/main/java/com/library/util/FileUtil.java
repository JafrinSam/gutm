package com.library.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> readFromFile(String filename, TypeReference<List<T>> typeRef) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
            return List.of();
        }
        return objectMapper.readValue(file, typeRef);
    }

    public static <T> void writeToFile(String filename, List<T> data) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), data);
    }
}
