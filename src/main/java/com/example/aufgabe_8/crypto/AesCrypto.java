package com.example.aufgabe_8.crypto;

import com.example.aufgabe_8.hash.FileHandler;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class AesCrypto {
    private final static String ALGORITHM_NAME = "AES/GCM/NoPadding";
    private final static int ALGORITHM_NONCE_SIZE = 12;
    private final static int ALGORITHM_TAG_SIZE = 128;
    private final static int ALGORITHM_KEY_SIZE = 128;
    private final static String PBKDF2_NAME = "PBKDF2WithHmacSHA256";
    private final static int PBKDF2_SALT_SIZE = 16;
    private final static int PBKDF2_ITERATIONS = 32767;
    FileHandler secret;
    FileHandler key;
    FileHandler out;

    public AesCrypto(String secretPath, String keyPath, String outPath) {
        secret = new FileHandler(secretPath);
        key = new FileHandler(keyPath);
        out = new FileHandler(outPath);
    }

    public static String encryptString(String plaintext, String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        // Generate a 128-bit salt using a CSPRNG.
        SecureRandom rand = new SecureRandom();
        byte[] salt = new byte[PBKDF2_SALT_SIZE];
        rand.nextBytes(salt);

        System.out.println("Salt: " + DatatypeConverter.printHexBinary(salt).toUpperCase());

        // Create an instance of PBKDF2 and derive a key.
        PBEKeySpec pwSpec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, ALGORITHM_KEY_SIZE);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_NAME);
        byte[] key = keyFactory.generateSecret(pwSpec).getEncoded();

        System.out.println("Generated Key: " + DatatypeConverter.printHexBinary(key).toUpperCase());
        System.out.println("Encrypting...");

        // Encrypt and prepend salt.
        byte[] ciphertextAndNonce = encrypt(plaintext.getBytes(StandardCharsets.UTF_8), key);
        byte[] ciphertextAndNonceAndSalt = new byte[salt.length + ciphertextAndNonce.length];
        System.arraycopy(salt, 0, ciphertextAndNonceAndSalt, 0, salt.length);
        System.arraycopy(ciphertextAndNonce, 0, ciphertextAndNonceAndSalt, salt.length, ciphertextAndNonce.length);

        // Return as base64 string.
        return Base64.getEncoder().encodeToString(ciphertextAndNonceAndSalt);
    }

    public static String decryptString(String base64CiphertextAndNonceAndSalt, String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
            NoSuchPaddingException {
        // Decode the base64.
        byte[] ciphertextAndNonceAndSalt = Base64.getDecoder().decode(base64CiphertextAndNonceAndSalt);

        // Retrieve the salt and ciphertextAndNonce.
        byte[] salt = new byte[PBKDF2_SALT_SIZE];
        byte[] ciphertextAndNonce = new byte[ciphertextAndNonceAndSalt.length - PBKDF2_SALT_SIZE];
        System.arraycopy(ciphertextAndNonceAndSalt, 0, salt, 0, salt.length);
        System.arraycopy(ciphertextAndNonceAndSalt, salt.length, ciphertextAndNonce, 0, ciphertextAndNonce.length);

        System.out.println("Extracted salt: " + DatatypeConverter.printHexBinary(salt).toUpperCase());
        // Create an instance of PBKDF2 and derive the key.
        PBEKeySpec pwSpec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, ALGORITHM_KEY_SIZE);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_NAME);
        byte[] key = keyFactory.generateSecret(pwSpec).getEncoded();

        System.out.println("Generated Key: " + DatatypeConverter.printHexBinary(key).toUpperCase());

        // Decrypt and return result.
        return new String(decrypt(ciphertextAndNonce, key), StandardCharsets.UTF_8);
    }

    public static byte[] encrypt(byte[] plaintext, byte[] key)
            throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        // Generate a 96-bit nonce using a CSPRNG.
        SecureRandom rand = new SecureRandom();
        byte[] nonce = new byte[ALGORITHM_NONCE_SIZE];
        rand.nextBytes(nonce);

        System.out.println("Nonce: " + DatatypeConverter.printHexBinary(nonce).toUpperCase());

        // Create the cipher instance and initialize.
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(key, "AES"),
                new GCMParameterSpec(ALGORITHM_TAG_SIZE,
                        nonce));

        // Encrypt and prepend nonce.
        byte[] ciphertext = cipher.doFinal(plaintext);

        System.out.println("Cipher: " + DatatypeConverter.printHexBinary(ciphertext).toUpperCase());
        byte[] ciphertextAndNonce = new byte[nonce.length + ciphertext.length];
        System.arraycopy(nonce, 0, ciphertextAndNonce, 0, nonce.length);
        System.arraycopy(ciphertext, 0, ciphertextAndNonce, nonce.length, ciphertext.length);

        return ciphertextAndNonce;
    }

    public static byte[] decrypt(byte[] ciphertextAndNonce, byte[] key)
            throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        // Retrieve the nonce and ciphertext.
        System.out.println("Decrypting...");
        byte[] nonce = new byte[ALGORITHM_NONCE_SIZE];
        byte[] ciphertext = new byte[ciphertextAndNonce.length - ALGORITHM_NONCE_SIZE];
        System.arraycopy(ciphertextAndNonce, 0, nonce, 0, nonce.length);
        System.arraycopy(ciphertextAndNonce, nonce.length, ciphertext, 0, ciphertext.length);

        System.out.println("Extracted nonce: " + DatatypeConverter.printHexBinary(nonce).toUpperCase());
        System.out.println("Extracted data: " + DatatypeConverter.printHexBinary(ciphertext).toUpperCase());


        // Create the cipher instance and initialize.
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE,
                new SecretKeySpec(key, "AES"),
                new GCMParameterSpec(ALGORITHM_TAG_SIZE, nonce));

        // Decrypt and return result.
        return cipher.doFinal(ciphertext);
    }

    public void encryptFile() {
        System.out.println("Verschlüssle Datei");
        try {
            secret.read();
            key.read();

            String data = new String(secret.getData(), StandardCharsets.UTF_8);
            String pass = new String(key.getData(), StandardCharsets.UTF_8);

            String encryptString = encryptString(data, pass);
            System.out.println("Write to file");
            out.write(encryptString);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException |
                InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException |
                BadPaddingException e) {
            e.printStackTrace();
        }

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

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException |
                InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException |
                NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }
}
