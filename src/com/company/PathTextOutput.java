package com.company;

public class PathTextOutput {

    static String print(int curX, int curY, int lastX, int lastY) {
        String text = "";
        if (curX > lastX) {
            text = "d";
        }
        if (curX < lastX) {
            text = "u";
        }
        if (curY > lastY) {
            text = "r";
        }
        if (curY < lastY) {
            text = "l";
        }
        return text;
    }

}
