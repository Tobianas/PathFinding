package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class AStarPathfindingAlgorithm {

    static int start[] = new int[2];
    static int finish[] = new int[2];
    Node[][] map = Create2DField.make2dField(AbstractFindPathInputReader.getImput()); //STDinput
    //Node[][] map = Create2DField.make2dField(FindPathInputReaderFile.getImput());     //FileImput
    boolean solving = true;


    public void AStar() {
        ArrayList<Node> priority = new ArrayList<Node>();


        priority.add(map[start[0]][start[1]]);
        while (solving) {
            if (priority.size() <= 0) {
                break;
            }
            int hops = priority.get(0).getHops() + 1;
            ArrayList<Node> explored = exploreNeighbors(priority.get(0), hops);
            if (explored.size() > 0) {
                priority.remove(0);
                priority.addAll(explored);
            } else {
                priority.remove(0);
            }
            sortQue(priority);    //SORT THE PRIORITY QUE
        }
    }

    public ArrayList<Node> sortQue(ArrayList<Node> sort) {    //SORT PRIORITY QUE
        int c = 0;
        while (c < sort.size()) {
            int sm = c;
            for (int i = c + 1; i < sort.size(); i++) {
                if (sort.get(i).getEuclidDist() + sort.get(i).getHops() < sort.get(sm).getEuclidDist() + sort.get(sm).getHops())
                    sm = i;
            }
            if (c != sm) {
                Node temp = sort.get(c);
                sort.set(c, sort.get(sm));
                sort.set(sm, temp);
            }
            c++;
        }
        return sort;
    }

    public ArrayList<Node> exploreNeighbors(Node current, int hops) {    //EXPLORE NEIGHBORS
        ArrayList<Node> explored = new ArrayList<Node>();    //LIST OF NODES THAT HAVE BEEN EXPLORED
        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                int xbound = current.getX() + a;
                int ybound = current.getY() + b;
                //STOP CROSS PATH
                if (a == 1) {
                    xbound = current.getX() + a;
                    ybound = current.getY();
                }
                if (a == -1) {
                    xbound = current.getX() + a;
                    ybound = current.getY();
                }
                if (b == 1) {
                    xbound = current.getX();
                    ybound = current.getY() + b;
                }
                if (b == -1) {
                    xbound = current.getX();
                    ybound = current.getY() + b;
                }
                /*int xbound = current.getX()+a;
                int ybound = current.getY()+b;*/
                if ((xbound > -1 && xbound < map.length) && (ybound > -1 && ybound < map[0].length && ((a != 1 && b != 1) || (a != -1 && b != -1) || (a != -1 && b != 1) || (a != 1 && b != -1)))) {    //MAKES SURE THE NODE IS NOT OUTSIDE THE GRID AND STOP CROSS PATH
                    Node neighbor = map[xbound][ybound];
                    if ((neighbor.getHops() == -1 || neighbor.getHops() > hops) && neighbor.getType() != 2 && ((a != 1 && b != 1) || (a != -1 && b != -1) || (a != -1 && b != 1) || (a != 1 && b != -1))) {    //CHECKS IF THE NODE IS NOT A WALL AND THAT IT HAS NOT BEEN EXPLORED
                        explore(neighbor, current.getX(), current.getY(), hops);    //EXPLORE THE NODE
                        explored.add(neighbor);    //ADD THE NODE TO THE LIST
                    }
                }
            }
        }
        return explored;
    }

    public void explore(Node current, int lastx, int lasty, int hops) {    //EXPLORE A NODE
        if (current.getType() != 0 && current.getType() != 1)    //CHECK THAT THE NODE IS NOT THE START OR FINISH
            current.setType(4);    //SET IT TO EXPLORED
        current.setLastNode(lastx, lasty);    //KEEP TRACK OF THE NODE THAT THIS NODE IS EXPLORED FROM
        current.setHops(hops);    //SET THE HOPS FROM THE START
        if (current.getType() == 1) {    //IF THE NODE IS THE FINISH THEN BACKTRACK TO GET THE PATH
            backtrack(current.getLastX(), current.getLastY(), hops);
            System.out.println("finish cordinates: " + current.getX() + " " + current.getY());
        }
    }

    public void backtrack(int lx, int ly, int hops) {         //BACKTRACK
        ArrayList<String> path = new ArrayList<>();
        while (hops > 1) {    //BACKTRACK FROM THE END OF THE PATH TO THE START
            Node current = map[lx][ly];
            current.setType(5);
            System.out.println(lx + " " + ly);
            lx = current.getLastX();
            ly = current.getLastY();

            path.add(PathTextOutput.print(current.getX(), current.getY(), current.getLastX(), current.getLastY()));
            //PathTextOutput.print(current.getX(),current.getY(),lx,ly);
            hops--;
        }
        Collections.reverse(path);
        System.out.println(path);
        System.out.println("solved");
        solving = false;
    }


    public static void setStart(int[] start) {
        AStarPathfindingAlgorithm.start = start;
    }

    public static void setFinish(int[] finish) {
        AStarPathfindingAlgorithm.finish = finish;
    }

    public static int[] getStart() {
        return start;
    }

    public static int[] getFinish() {
        return finish;
    }


}
