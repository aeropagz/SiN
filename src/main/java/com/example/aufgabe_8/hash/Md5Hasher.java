package com.example.aufgabe_8.hash;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.jcajce.provider.digest.SHA3;

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
            read();
        } catch (IOException e) {
            System.out.println("Ungültiger Dateipfad");
            return null;
        }

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        SHA3.DigestSHA3 sha3 = new SHA3.Digest512();
        RIPEMD160Digest ripe = new RIPEMD160Digest();


        ripe.update(data, 0, data.length);
        byte[] ripeOut = new byte[ripe.getDigestSize()];

        ripe.doFinal(ripeOut, 0);

        byte[] digestSHA3 = sha3.digest(data);

        md5.update(data);
        byte[] digestMd5 = md5.digest();
        String hashMd5 = DatatypeConverter.printHexBinary(digestMd5).toUpperCase();

        System.out.println("SHA3: " + DatatypeConverter.printHexBinary(digestSHA3).toUpperCase());
        System.out.println("RIPE " + DatatypeConverter.printHexBinary(ripeOut).toUpperCase());

        System.out.println("MD5: " + hashMd5);
        return hashMd5;
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
