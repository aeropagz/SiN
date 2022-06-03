package com.example.aufgabe_8.hash;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
public class FileHandler {
    final String filepath;
    byte[] data;

    void open() throws IOException {
        data = Files.readAllBytes(Path.of(filepath));
    }
}
