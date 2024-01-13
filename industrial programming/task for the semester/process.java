package com.example.spring_semtask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public process(String input_file_name_, String output_file_name_) throws IOException {
        input_file_name = input_file_name_;
        output_file_name = output_file_name_;
    }

    public void replace() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            String modifiedContent = replaceArithmeticOperations(content.toString());

            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(modifiedContent);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replaceArithmeticOperations(String content) {
        String regex = "([\\d.]+)\\s*([+\\-*/])\\s*([\\d.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            double operand1 = Double.parseDouble(matcher.group(1));
            String operator = matcher.group(2);
            double operand2 = Double.parseDouble(matcher.group(3));

            double result = 0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
            }

            content = content.replace(matcher.group(), Double.toString(result));
        }

        return content;
    }

}
