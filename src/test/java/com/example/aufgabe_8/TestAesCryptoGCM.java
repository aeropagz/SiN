package com.example.aufgabe_8;

import com.example.aufgabe_8.crypto.AesCryptoGCM;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class TestAesCryptoGCM {

    @Test
    public void givenString_whenEncrypt_thenSuccess() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeySpecException {

        String input = "baeldung";
        String encryptString = AesCryptoGCM.encryptString(input, "password");
        String plainText = AesCryptoGCM.decryptString(encryptString, "password");
        Assertions.assertEquals(input, plainText);
    }
}
