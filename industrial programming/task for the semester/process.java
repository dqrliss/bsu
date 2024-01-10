package com.example.spring_semtask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//import org.springframework.stereotype.Service;

//@Service
public class process {
    private String input_file_name;
    private String output_file_name;
    private String file_content;

    public process(String input_file_name_, String output_file_name_) throws IOException {
        input_file_name = input_file_name_;
        output_file_name = output_file_name_;
        file_content = Files.readString(Paths.get(input_file_name));
        System.out.println(file_content);
    }

    public void replace() {
        try (BufferedReader reader = new BufferedReader(new FileReader(input_file_name));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output_file_name))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String result = evaluateExpression(line);
                writer.write(result);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String evaluateExpression(String expression) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try {
            Object result = engine.eval(expression);
            return result.toString();
        } catch (ScriptException e) {
            return "Ошибка в выражении";
        }
    }

}
