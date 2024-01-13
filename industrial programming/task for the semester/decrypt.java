package com.example.spring_semtask;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
//import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Base64;


public class decrypt {
    private String input_file_name;
    private String new_input_file_name;

    public decrypt (String input_file_name_) {
        input_file_name = input_file_name_;
        new_input_file_name = "input" + input_file_name.substring(input_file_name.indexOf('.'));
        System.out.println(new_input_file_name);
    }
    public String decr() {
        try {

            String secretKeyString = "ThisIsASecretKey";
            SecretKey secretKey = new SecretKeySpec(secretKeyString.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] encryptedBytes = Files.readAllBytes(Paths.get(input_file_name));

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            Path decryptedPath = Paths.get(new_input_file_name);
            Files.write(decryptedPath, decryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new_input_file_name;
    }
}
