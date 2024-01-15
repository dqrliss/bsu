package com.example.sem_project_javafx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class for_txt {
    private String fileName;

    public for_txt(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readingFromPlain(String fileName)
    {
        List<String> gainData = new ArrayList<>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(fileName));

            String tmpLine;
            while((tmpLine = br.readLine()) != null)
            {
                String[] expressions = tmpLine.split(";");
                for (int i = 0; i < expressions.length; i++)
                    gainData.add(expressions[i]);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error" + e);
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Error" + e);
            }
        }

        return gainData;
    }

    public void writeInPlain(String outFileName, String integerVector)
    {
        try(FileWriter fileWriter = new FileWriter(outFileName))
        {
            fileWriter.write(integerVector);
        }
        catch (IOException exp)
        {
            System.out.println("Error" + exp);
        }
    }
}

