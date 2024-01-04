package com.example.presents_javafx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class for_file {
    static ArrayList <for_gifts> factories = new ArrayList<>();
    static int count;
    public static void read(String fileName) throws Exception {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine();
        count = Integer.parseInt(str);

        for (int i = 0; i < count; i++) {
            br.readLine();
            for_gifts factory = new for_gifts();
            factory.readFactory(br);
            factories.add(factory);
        }
    }
}
