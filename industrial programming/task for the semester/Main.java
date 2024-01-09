package com.example;

import java.io.IOException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {
        try {
            String secretKey = "YourSecretKey123";
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] fileData = Files.readAllBytes(new File("input.txt").toPath());

            byte[] encryptedData = cipher.doFinal(fileData);

            FileOutputStream outputStream = new FileOutputStream("encrypted_output.enc");
            outputStream.write(encryptedData);
            outputStream.close();

            System.out.println("Файл успешно зашифрован.");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
        }
    }
}