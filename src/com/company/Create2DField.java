package com.company;

import java.util.ArrayList;

public class Create2DField {


    static Node[][] make2dField(ArrayList array) {

        // creating 2D array from imput
        String[][] test = new String[array.size()][array.get(0).toString().length()];
        Node[][] node = new Node[array.size()][array.get(0).toString().length()];

        // y
        for (int i = 0; i < array.size(); i++) {
            // x
            for (int j = 0; j < array.get(i).toString().length(); j++) {
                char value = array.get(i).toString().charAt(j);
                if ('.' == (value)) {
                    node[i][j] = new Node(3, i, j);
                }
                if ('#' == (value)) {
                    node[i][j] = new Node(2, i, j);
                }
                if ('S' == (value)) {
                    node[i][j] = new Node(0, i, j);
                    int[] start = {i, j};
                    AStarPathfindingAlgorithm.setStart(start);
                }
                if ('X' == (value)) {
                    node[i][j] = new Node(1, i, j);
                }

            }


        }
        return node;
    }

}
