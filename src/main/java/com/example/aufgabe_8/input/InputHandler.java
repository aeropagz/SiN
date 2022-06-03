package com.example.aufgabe_8.input;

import com.example.aufgabe_8.crypto.AesCrypto;
import com.example.aufgabe_8.hash.Md5Hasher;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class InputHandler {
    private static Scanner input;

    public static void chooseAction() throws NoSuchAlgorithmException {
        int eingabe = 404;
        input = new Scanner(System.in).useDelimiter("\n");

        while (eingabe != 0) {
            System.out.println("""
                    Was möchten Sie machen?
                    [1] Datei verschlüsseln
                    [2] Datei entschlüsseln
                    [3] Datai hashen
                    [4] Datei-Hash prüfen
                    [0] Programm beenden
                    """);
            try {
                eingabe = input.nextInt();
                switch (eingabe) {
                    case 0 -> {
                        input.close();
                        System.out.println("Programm wird beendet...");
                        return;
                    }
                    case 1 -> {
                        AesCrypto aesCrypto = setupCrypto();
                        aesCrypto.encrypt();
                    }
                    case 2 -> {
                        AesCrypto aesCrypto = setupCrypto();
                        aesCrypto.decrypt();
                    }
                    case 3 -> {
                        String filePath = getFilePath();
                        Md5Hasher md5Hasher = new Md5Hasher(filePath);
                        md5Hasher.hash();
                    }
                    case 4 -> {
                        String filePath = getFilePath();
                        Md5Hasher md5Hasher = new Md5Hasher(filePath);
                        md5Hasher.checkHash();
                    }
                    default -> throw new RuntimeException();
                }
            } catch (RuntimeException exception) {
                System.out.println("Ungültige Eingabe, bitte erneut versuchen!\n");
                input.nextLine();
            }
        }
    }

    static AesCrypto setupCrypto() {
        System.out.println("Secret Datei:");
        String secretPath = getFilePath();

        System.out.println("Key Datei:");
        String keyPath = getFilePath();

        System.out.println("Output");
        String outPath = getFilePath();

        return new AesCrypto(secretPath, keyPath, outPath);
    }

    static String getFilePath() {
        System.out.println("Bitte geben Sie einen Pfad zur Datei an.");
        return input.next();
    }
}
