package com.example.aufgabe_8;

import com.example.aufgabe_8.crypto.AesCrypto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class TestAesCrypto {

    @Test
    public void givenString_whenEncrypt_thenSuccess() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeySpecException {

        String input = "baeldung";
        String encryptString = AesCrypto.encryptString(input, "password");
        String plainText = AesCrypto.decryptString(encryptString, "password");
        Assertions.assertEquals(input, plainText);
    }
}
