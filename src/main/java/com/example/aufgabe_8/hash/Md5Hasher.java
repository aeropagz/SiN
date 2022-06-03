package com.example.aufgabe_8.hash;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hasher extends FileHandler {
    public Md5Hasher(String filepath) {
        super(filepath);
    }

    public void hash() throws NoSuchAlgorithmException {
        try {
            open();
        } catch (IOException e) {
            System.out.println("Ungültiger Dateipfad");
            return;
        }
        System.out.println("Starte hashing...");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data);
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        System.out.println(myHash);
    }

    public void checkHash() {
        System.out.println("Kontrollier Hash...");
    }
}
