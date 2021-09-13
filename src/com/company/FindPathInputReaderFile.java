package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FindPathInputReaderFile {
    static ArrayList getImput() {
        try {
            BufferedReader br = null;
            String line;
            ArrayList<String> arrayLines = new ArrayList();

            br = new BufferedReader(new FileReader("TextFile.txt"));
            while ((line = br.readLine()) != null) {
                arrayLines.add(line);
            }
            return arrayLines;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
