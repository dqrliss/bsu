package com.example.kr_2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Service
public class calculate_union {
    public String uni(String str1, String str2) {

        String[] words1 = str1.split("[\\s,.:;?!-]+");
        String[] words2 = str2.split("[\\s,.:;?!-]+");

        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();

        for (String word: words1) if (word.matches("[a-zA-Z]+") && !word.isEmpty()) arr1.add(word);
        for (String word: words2) if (word.matches("[a-zA-Z]+") && !word.isEmpty()) arr2.add(word);

        Set<String> union_of_two = new HashSet<>(arr1);
        union_of_two.addAll(arr2);

        StringBuilder result = new StringBuilder("result:\n");
        for (String word: union_of_two) result.append(word + "\n");
        if (union_of_two.isEmpty()) result.append("no unions");

        return result.toString();
    }
}
