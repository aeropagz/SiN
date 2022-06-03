package com.example.aufgabe_8.crypto;

import com.example.aufgabe_8.hash.FileHandler;

public class AesCrypto {
    FileHandler secret;
    FileHandler key;

    public AesCrypto(String secretPath, String keyPath, String outPath) {
        secret = new FileHandler(secretPath);
        key = new FileHandler(keyPath);
    }

    public void encrypt() {
        System.out.println("Verschlüssele Datei");
    }

    public void decrypt() {
        System.out.println("Entschlüssele Datei");
    }
}
