package com.iupui.clarkmd;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Array;
import java.util.TreeMap;
import java.util.*;

public class Main {

    public static void main(String[] args){

        //create a gameboard
        Node initialNode = new Node();

        if(args[0].equals("BFS")) {
            //Do BFS search
            BreadthFirstSearch newBFSSearch = new BreadthFirstSearch();

            //Make sure board is solvable
            newBFSSearch.checkIfSolvable(initialNode);

            //put gameboard in the queue
            newBFSSearch.frontier.add(initialNode);

            // START searching the queue and generating successors

            int bfsDepth = 1;
            while (newBFSSearch.frontier.peek() != null) {
                if (newBFSSearch.frontier.peek().getDepth() != bfsDepth) {
                    bfsDepth = newBFSSearch.frontier.peek().getDepth();
                    System.out.print(bfsDepth + " ");
                }
                newBFSSearch.moveLeft(newBFSSearch.frontier.peek());
                newBFSSearch.moveRight(newBFSSearch.frontier.peek());
                newBFSSearch.moveUp(newBFSSearch.frontier.peek());
                newBFSSearch.moveDown(newBFSSearch.frontier.poll());
            }

            if (newBFSSearch.frontier.peek() == null) {
                System.out.println("NO SOLUTION TO BOARD");
            }
        }else if (args[0].equals("AStar")) {
            //Do A* search
            AStarSearch newAStarSearch = new AStarSearch();

            //Check if Solvable
            newAStarSearch.checkIfSolvable(initialNode);

            //put gameboard in the queue
            newAStarSearch.frontier.add(initialNode);


            // START searching the queue and generating successors
            while (newAStarSearch.frontier.peek() != null) {
//            if(newAStarSearch.explored.size()%1000 == 0){
//                System.out.println(newAStarSearch.explored.size());
//            }
                //see if is the goal state if so this will exit program
                newAStarSearch.isGoalState(newAStarSearch.frontier.peek());
                newAStarSearch.moveLeft(newAStarSearch.frontier.peek());
                newAStarSearch.moveRight(newAStarSearch.frontier.peek());
                newAStarSearch.moveUp(newAStarSearch.frontier.peek());
                newAStarSearch.moveDown(newAStarSearch.frontier.peek());
                newAStarSearch.explored.add(newAStarSearch.frontier.poll());
            }
        }else {
            System.out.println("No Arguments Either use BFS or A* as the arguments");
        }
    }//end main

}
