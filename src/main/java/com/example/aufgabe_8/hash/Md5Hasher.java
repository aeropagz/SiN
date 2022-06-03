package com.example.aufgabe_8.hash;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hasher extends FileHandler {
    public Md5Hasher(String filepath) {
        super(filepath);
    }

    public String hash() throws NoSuchAlgorithmException {
        System.out.println("Starte hashing...");
        try {
            open();
        } catch (IOException e) {
            System.out.println("Ungültiger Dateipfad");
            return null;
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data);
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        System.out.println("Hash: " + myHash);
        return myHash;
    }

    public void checkHash(String check) throws NoSuchAlgorithmException {
        String hash = hash();
        if (hash == null) {
            return;
        }
        if (hash.equals(check)) {
            System.out.println("Hash ist korrekt");
        } else {
            System.out.println("Hash ist falsch!");
        }
    }
}
