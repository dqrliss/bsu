package com.example.kr_2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class calculate_latin {
    public String latin(String text) {

        ArrayList <String> arr = new ArrayList<>();
        if (text.isEmpty()) return "text is absent";

        String[] words = text.split("[\\s,.:;!?-]+");

        for (String word: words) if (word.matches("[a-zA-Z]+") && !word.isEmpty()) arr.add(word);

        StringBuilder result = new StringBuilder("result:\n");
        for (String i: arr) result.append(i + "\n");

        return result.toString();
    }
}