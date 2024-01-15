package com.example.sem_project_javafx;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class for_encrypt_and_decrypt {
    private static SecretKey secretKey;
    private static Cipher cipher;

    for_encrypt_and_decrypt(SecretKey secretKey, String transformation) {
        for_encrypt_and_decrypt.secretKey = secretKey;
        try {
            cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
    void encrypt(String inFileName, String content, String outfileName) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(outfileName + ".enc");
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);

            cipherOut.write(inFileName.getBytes());
            cipherOut.write(content.getBytes());
        }
    }

    String decrypt(String fileName) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
        String content;

        try (FileInputStream fileIn = new FileInputStream(fileName)) {
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    InputStreamReader inputReader = new InputStreamReader(cipherIn);
                    BufferedReader reader = new BufferedReader(inputReader)
            ) {

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
            }

        }
        return content;
    }

    void encryptInputFile(String inFileName, String inFileData) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(inFileName + ".enc");
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);

            cipherOut.write(inFileName.getBytes());
            cipherOut.write(".".getBytes());
            cipherOut.write(inFileData.getBytes());
            cipherOut.write("SEPARATOR".getBytes());

            try
            {
                Scanner sc = new Scanner(new File(inFileName + "." + inFileData));

                while(sc.hasNext())
                {
                    String tmpLine = sc.nextLine();
                    cipherOut.write(tmpLine.getBytes());
                }
            }
            catch (IOException e)
            {
                System.out.println("Error" + e);
            }
        }
    }

    void encryptInputArchive(String inFileName, String inFileData) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(inFileName + ".enc");
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);

            cipherOut.write(inFileName.getBytes());
            cipherOut.write(".".getBytes());
            cipherOut.write(inFileData.getBytes());
            cipherOut.write("SEPARATOR".getBytes());

            for_archiving zfw = new for_archiving();
            String unpackedfile = zfw.archiveInput(inFileName + "." + inFileData);

            cipherOut.write(unpackedfile.getBytes());
            cipherOut.write("SEPARATOR".getBytes());

            try
            {
                Scanner sc = new Scanner(new File(unpackedfile));

                while(sc.hasNext())
                {
                    String tmpLine = sc.nextLine();
                    cipherOut.write(tmpLine.getBytes());
                }
            }
            catch (IOException e)
            {
                System.out.println("Error" + e);
            }

            File fileToDelete = new File(unpackedfile);
            fileToDelete.delete();
        }
    }
}