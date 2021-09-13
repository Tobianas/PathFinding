package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class AbstractFindPathInputReader {


    static ArrayList getImput() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arrayLines = new ArrayList<>();
        String line;
        //while input is not empty then loop
        while (true) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            } else {
                arrayLines.add(line);
            }
        }
        return arrayLines;
    }
}


