package com.iupui.clarkmd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import com.iupui.clarkmd.*;
/**
 * Created by Zues on 2/20/15.
 */
public class BreadthFirstSearch {

    //queue to hold each board
    public Queue<Node> frontier = new LinkedList<Node>();
    //list of already generated boards
    public ArrayList<String> explored = new ArrayList<String>();
    // goal state to check against
    private final String goalState = "123456780";

    //Constructor print the search type
    public BreadthFirstSearch(){
        System.out.print("Starting Breadth First Search - Depth: ");
    }

    //Takes the current board initial or top of queue and makes a copy of it places copy in queue.
    public void moveLeft(Node currentBoard){
        //Move blank space left current position of blank space -1
        int freeSpace = currentBoard.getShuffledBoard().indexOf(0);
        if(freeSpace != 0 && freeSpace != 3 && freeSpace !=6){
            //then its valid move
            Node copyOfBoard = new Node(currentBoard);
            int temp = copyOfBoard.getShuffledBoard().get(freeSpace - 1);
            copyOfBoard.getShuffledBoard().set(freeSpace, temp);
            copyOfBoard.getShuffledBoard().set(freeSpace - 1, 0);
            isGoalState(copyOfBoard);
        }
    }

    //Takes the current board initial or top of queue and makes a copy of it places copy in queue.
    public void moveRight(Node currentBoard){
        //Move blank space left current position of blank space -1
        int freeSpace = currentBoard.getShuffledBoard().indexOf(0);
        if(freeSpace != 2 && freeSpace != 5 && freeSpace !=8){
            //then its valid move
            Node copyOfBoard = new Node(currentBoard);
            int temp = copyOfBoard.getShuffledBoard().get(freeSpace + 1);
            copyOfBoard.getShuffledBoard().set(freeSpace, temp);
            copyOfBoard.getShuffledBoard().set(freeSpace + 1, 0);
            isGoalState(copyOfBoard);
        }
    }

    //Takes the current board initial or top of queue and makes a copy of it places copy in queue.
    public void moveUp(Node currentBoard){
        //Move blank space left current position of blank space -1
        int freeSpace = currentBoard.getShuffledBoard().indexOf(0);
        if(freeSpace > 2){
            //then its valid move
            Node copyOfBoard = new Node(currentBoard);
            int temp = copyOfBoard.getShuffledBoard().get(freeSpace - 3);
            copyOfBoard.getShuffledBoard().set(freeSpace, temp);
            copyOfBoard.getShuffledBoard().set(freeSpace - 3, 0);
            isGoalState(copyOfBoard);
        }
    }

    //Takes the current board initial or top of queue and makes a copy of it places copy in queue.
    public void moveDown(Node currentBoard){
        //Move blank space left current position of blank space -1
        int freeSpace = currentBoard.getShuffledBoard().indexOf(0);
        if(freeSpace < 6){
            //then its valid move
            Node copyOfBoard = new Node(currentBoard);
            int temp = copyOfBoard.getShuffledBoard().get(freeSpace + 3);
            copyOfBoard.getShuffledBoard().set(freeSpace, temp);
            copyOfBoard.getShuffledBoard().set(freeSpace + 3, 0);
            isGoalState(copyOfBoard);
        }
    }

    public void isGoalState(Node newBoard){
        String newBoardString = "";
        for(Integer boardInt: newBoard.getShuffledBoard()){
            newBoardString += boardInt.toString();
        }
        if(newBoardString.contentEquals(this.goalState)){
            System.out.println("\nFound Solution at Depth = " + newBoard.getDepth() + " Generated " + this.explored.size() + " nodes");
            System.out.println("Walking from goal to initial");
            printGameBoard(newBoard);
            int goalDepth = newBoard.getDepth();
            for(int i=0; i<goalDepth; i++){
                printGameBoard(newBoard.getParent());
                newBoard = newBoard.getParent();
            }
            System.exit(0);
        }else{
            if(this.explored.contains(newBoardString)){
                //already have seen the board here for debug purposes.
            }else{
                this.explored.add(newBoardString);
                this.frontier.add(newBoard);
            }
        }
    }

    public void checkIfSolvable(Node initalNode){
        int inversions = 0;
        for(int i=3; i<4; i++){
            for(int j=i+1; j<9; j++){
                if(initalNode.getShuffledBoard().get(j) > initalNode.getShuffledBoard().get(i) && initalNode.getShuffledBoard().get(i) != 0 && initalNode.getShuffledBoard().get(j) !=0){
                    inversions = inversions + 1;
                }
            }
        }
        if(inversions%2 == 1){
            System.out.println("NO SOLUTION FOR BOARD");
            System.exit(0);
        }
    }

    //Print the board passed to it
    public void printGameBoard(Node currentGameBoard) {
        System.out.println(currentGameBoard.getShuffledBoard().get(0) + " " + currentGameBoard.getShuffledBoard().get(1) + " " + currentGameBoard.getShuffledBoard().get(2));
        System.out.println(currentGameBoard.getShuffledBoard().get(3) + " " + currentGameBoard.getShuffledBoard().get(4) + " " + currentGameBoard.getShuffledBoard().get(5));
        System.out.println(currentGameBoard.getShuffledBoard().get(6) + " " + currentGameBoard.getShuffledBoard().get(7) + " " + currentGameBoard.getShuffledBoard().get(8));
        System.out.println("");
    }
}
