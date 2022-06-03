package com.example.aufgabe_8.hash;

import lombok.Data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
public class FileHandler {
    final String filepath;
    byte[] data;

    public void read() throws IOException {
        data = Files.readAllBytes(Path.of(filepath));
    }

    public void write(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(data);
        writer.close();
    }
}
