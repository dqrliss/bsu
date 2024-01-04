package com.example.presents_javafx;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class for_gifts {
    ArrayList<Gift> gifts = new ArrayList<>();
    String name;
    int quantity;

    public void readFactory(BufferedReader br) throws Exception {
        String str = br.readLine();
        String regex = "([\\s\\S]+?)(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            name = matcher.group(1);
            quantity = Integer.parseInt(matcher.group(2));
        }
        else throw new Exception();
        for (int i = 0; i < quantity; i++) {
            Gift gift = new Gift();
            gift.readGift(br);
            gifts.add(gift);
        }
    }

    public class Gift {
        String name;
        int cost;

        public void readGift(BufferedReader br) throws Exception {
            String str = br.readLine();
            String regex = "([\\s\\S]+?)(\\d+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                name = matcher.group(1);
                cost = Integer.parseInt(matcher.group(2));
            }
            else throw new Exception();
        }
    }
}