package com.example.aufgabe_8.crypto;

import com.example.aufgabe_8.hash.FileHandler;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class AesCrypto {
    FileHandler secret;
    FileHandler key;
    FileHandler out;

    public AesCrypto(String secretPath, String keyPath, String outPath) {
        secret = new FileHandler(secretPath);
        key = new FileHandler(keyPath);
        out = new FileHandler(outPath);
    }


    public void encryptFile() {
        System.out.println("Verschlüssle Datei");
        try {
            secret.read();
            key.read();

            String data = new String(secret.getData(), StandardCharsets.UTF_8);
            String pass = new String(key.getData(), StandardCharsets.UTF_8);

            String encryptString = encryptString(data, pass);
            System.out.println("Write to file: " + encryptString.length());
            out.write(encryptString);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    private static String encryptString(String data, String pass) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec key = new SecretKeySpec(pass.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        byte[] encodedBytes = Base64.getEncoder().encode(encryptedBytes);
        String encoded = new String(encodedBytes);
        System.out.println("Base64 Encoded Cipher: " + encoded);
        return encoded;
    }

    public void decryptFile() {
        try {
            secret.read();
            key.read();

            String data = new String(secret.getData(), StandardCharsets.UTF_8);
            String pass = new String(key.getData(), StandardCharsets.UTF_8);

            String decryptString = decryptString(data, pass);
            System.out.println("Write to file");
            out.write(decryptString);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException |
                BadPaddingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    private static String decryptString(String data, String pass) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec key = new SecretKeySpec(pass.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedData = data.getBytes(StandardCharsets.UTF_8);
        System.out.println("Decrypt: " + encryptedData.length);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] result = cipher.doFinal(decodedData);
        String decoded = new String(result);
        System.out.println("Decoded Secrtet: " + decoded);
        return decoded;
    }
}