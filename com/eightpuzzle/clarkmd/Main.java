package com.eightpuzzle.clarkmd;

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

            //Make sure board is solvable & put gameboard in the queue
            newBFSSearch
                .checkIfSolvable(initialNode)
                .frontier.add(initialNode);

            // START searching the queue and generating successors
            int bfsDepth = 1;
            while (newBFSSearch.frontier.peek() != null) {
                if (newBFSSearch.frontier.peek().getDepth() != bfsDepth) {
                    bfsDepth = newBFSSearch.frontier.peek().getDepth();
                    System.out.print(bfsDepth + " ");
                }
                newBFSSearch
                    .moveLeft(newBFSSearch.frontier.peek())
                    .moveRight(newBFSSearch.frontier.peek())
                    .moveUp(newBFSSearch.frontier.peek())
                    .moveDown(newBFSSearch.frontier.poll());
            }

            if (newBFSSearch.frontier.peek() == null) {
                System.out.println("NO SOLUTION TO BOARD");
            }
        }
        
        if (args[0].equals("AStar")) {
            //Do A* search
            AStarSearch newAStarSearch = new AStarSearch();

            newAStarSearch
                .checkIfSolvable(initialNode)
                .frontier.add(initialNode);

            // START searching the queue and generating successors
            while (newAStarSearch.frontier.peek() != null) {
                int peekedValue = newAStarSearch.frontier.peek();

                newAStarSearch
                    .isGoalState(peekedValue)
                    .moveLeft(peekedValue)
                    .moveRight(peekedValue)
                    .moveUp(peekedValue)
                    .moveDown(peekedValue)
                    .explored.add(newAStarSearch.frontier.poll());
            }
        }
    }//end main

}
