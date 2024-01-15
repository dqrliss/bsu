package com.example.sem_project_javafx;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProgramManager {
    calculate_helper calculator = new calculate_helper();
    Vector<Double> vectorOfResults = new Vector<>();

    public ProgramManager() throws NoSuchAlgorithmException {}

    public void manageInput(IOFileInfo ioFileInfo) throws InvalidAlgorithmParameterException, IOException, NoSuchAlgorithmException, InvalidKeyException {
        for_txt ptw = new for_txt(ioFileInfo.inputFileName);
        List<String> gainData = new ArrayList<>();

        switch (ioFileInfo.inputFileData) {
            case "txt" -> {
                switch (ioFileInfo.inputEncryptionMethod) {
                    case "1" -> {
                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        gainData = ptw.readingFromPlain(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        decryptFile(ioFileInfo);
                        gainData = ptw.readingFromPlain(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                    case "3" -> {
                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        decryptFile(ioFileInfo);

                        gainData = ptw.readingFromPlain(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "4" -> {
                        decryptArchive(ioFileInfo);

                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        gainData = ptw.readingFromPlain(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        gainData = ptw.readingFromPlain(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                }
            }
            case "json" -> {
                switch (ioFileInfo.inputEncryptionMethod) {
                    case "1" -> {
                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        for_json parser = new for_json();
                        gainData = parser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        decryptFile(ioFileInfo);
                        for_json parser = new for_json();
                        gainData = parser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                    case "3" -> {
                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        decryptFile(ioFileInfo);

                        for_json parser = new for_json();
                        gainData = parser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "4" -> {
                        decryptArchive(ioFileInfo);

                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        for_json parser = new for_json();
                        gainData = parser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        for_json parser = new for_json();
                        gainData = parser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                }
            }
            case "xml" -> {
                switch (ioFileInfo.inputEncryptionMethod) {
                    case "1" -> {
                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        for_xml xmlParser = new for_xml();
                        gainData = xmlParser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        decryptFile(ioFileInfo);
                        for_xml xmlParser = new for_xml();
                        gainData = xmlParser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                    case "3" -> {
                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        decryptFile(ioFileInfo);

                        for_xml xmlParser = new for_xml();
                        gainData = xmlParser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "4" -> {
                        decryptArchive(ioFileInfo);

                        for_archiving zfw = new for_archiving();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchiveData);

                        for_xml xmlParser = new for_xml();
                        gainData = xmlParser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        for_xml xmlParser = new for_xml();
                        gainData = xmlParser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                }
            }
        }

        switch (ioFileInfo.numberOfCalculationMethod) {
            case "1" -> {
                calculator.setCalc(new calculate_without_regexps());
            }
            case "2" -> {
                calculator.setCalc(new calculate_by_regexps());
            }
            case "3" -> {
                calculator.setCalc(new calculate_by_library());
            }
        }

        for (int i = 0; i < gainData.size(); i++) {
            double res = calculator.calculate(gainData.get(i));
            vectorOfResults.add(res);
        }
    }

    public void manageOutput(IOFileInfo ioFileInfo) throws InvalidKeyException, IOException, NoSuchAlgorithmException {
        for_txt ptw = new for_txt(ioFileInfo.inputFileName);

        switch (ioFileInfo.outputFileData) {
            case "txt" -> {
                switch (ioFileInfo.outputEncryptionMethod) {
                    case "1" -> {
                        ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", vectorOfResults.toString());

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        encryptFile(ioFileInfo);
                    }
                    case "3" -> {
                        ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", vectorOfResults.toString());

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();

                        ioFileInfo.outputFileData = ioFileInfo.outArchiveData;
                        encryptFile(ioFileInfo);
                    }
                    case "4" -> {
                        encryptFile(ioFileInfo);
                        ioFileInfo.outputFileData = "enc";

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", vectorOfResults.toString());
                    }
                }
            }
            case "json" -> {
                switch (ioFileInfo.outputEncryptionMethod) {
                    case "1" -> {
                        for_json parser = new for_json();
                        parser.writeInJson(ioFileInfo.outputFileName + ".json", vectorOfResults.toString());

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        encryptFile(ioFileInfo);
                    }
                    case "3" -> {
                        for_json parser = new for_json();
                        parser.writeInJson(ioFileInfo.outputFileName + ".json", vectorOfResults.toString());

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();

                        ioFileInfo.outputFileData = ioFileInfo.outArchiveData;
                        encryptFile(ioFileInfo);
                    }
                    case "4" -> {
                        encryptFile(ioFileInfo);
                        ioFileInfo.outputFileData = "enc";

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        for_json parser = new for_json();
                        parser.writeInJson(ioFileInfo.outputFileName + ".json", vectorOfResults.toString());
                    }
                }
            }
            case "xml" -> {
                switch (ioFileInfo.outputEncryptionMethod) {
                    case "1" -> {
                        for_xml xmlParser = new for_xml();
                        xmlParser.writeInXml(ioFileInfo.outputFileName, vectorOfResults);

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        encryptFile(ioFileInfo);
                    }
                    case "3" -> {
                        for_xml xmlParser = new for_xml();
                        xmlParser.writeInXml(ioFileInfo.outputFileName, vectorOfResults);

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();

                        ioFileInfo.outputFileData = ioFileInfo.outArchiveData;
                        encryptFile(ioFileInfo);
                    }
                    case "4" -> {
                        encryptFile(ioFileInfo);
                        ioFileInfo.outputFileData = "enc";

                        for_archiving zfw = new for_archiving();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchiveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        for_xml xmlParser = new for_xml();
                        xmlParser.writeInXml(ioFileInfo.outputFileName, vectorOfResults);
                    }
                }
            }
        }
    }


    private void encryptFile(IOFileInfo ioFileInfo) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        String key = "secretkey1111111";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

        for_encrypt_and_decrypt cipher = new for_encrypt_and_decrypt(secretKey, "AES/CBC/PKCS5Padding");
        File fileToEncrypt = new File(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
        cipher.encrypt(fileToEncrypt.toString(), vectorOfResults.toString(), ioFileInfo.outputFileName);
    }

    public void decryptFile(IOFileInfo ioFileInfo) throws InvalidAlgorithmParameterException, IOException, InvalidKeyException, NoSuchAlgorithmException {
        String key = "secretkey1111111";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

        for_encrypt_and_decrypt cipher = new for_encrypt_and_decrypt(secretKey, "AES/CBC/PKCS5Padding");
        String content = cipher.decrypt(ioFileInfo.inputFileName + ".enc");

        String[] file_name_content = content.split("SEPARATOR");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name_content[0]))) {
            writer.write(file_name_content[1] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decryptArchive(IOFileInfo ioFileInfo) throws InvalidAlgorithmParameterException, IOException, InvalidKeyException {
        String key = "secretkey1111111";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

        for_encrypt_and_decrypt cipher = new for_encrypt_and_decrypt(secretKey, "AES/CBC/PKCS5Padding");
        String content = cipher.decrypt(ioFileInfo.inputFileName + ".enc");

        String[] file_name_content = content.split("SEPARATOR");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name_content[1]))) {
            writer.write(file_name_content[2] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for_archiving zfw = new for_archiving();
        zfw.archiveOutput(ioFileInfo.inputFileName, ioFileInfo.inputFileData, ioFileInfo.inArchiveData);
        File fileToDelete = new File(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
        fileToDelete.delete();
    }
}
