package com.example.spring_semtask;
//import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

//@Service
public class encrypt {
    private String output_file_name;
    private String new_output_file_name;

    public encrypt(String output_file_name_){
        output_file_name = output_file_name_;
        new_output_file_name = "encrypted" + output_file_name.substring(output_file_name.indexOf('.'));
    }
    public String encr() {
        try {

            String secretKey = "ThisIsASecretKey";

            FileInputStream fileInputStream = new FileInputStream(output_file_name);

            byte[] inputBytes = new byte[(int) Files.size(Paths.get(output_file_name))];
            System.out.println(output_file_name.length());
            fileInputStream.read(inputBytes);

            Cipher cipher = Cipher.getInstance("AES");
            Key secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream fileOutputStream = new FileOutputStream(new_output_file_name);
            fileOutputStream.write(outputBytes);

            fileInputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new_output_file_name;
    }
}
