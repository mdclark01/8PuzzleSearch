package com.eightpuzzle.clarkmd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import com.eightpuzzle.clarkmd.*;

/**
 * Created by Michael Clark.
 */
public class AStarSearch {

    public Queue<Node> frontier = new PriorityQueue<Node>(nodeComparator);
    public ArrayList<Node> explored = new ArrayList<Node>();

    // goal state to check against
    public int[] goalState = {1,2,3,4,5,6,7,8,0};

    //Constructor print the search type
    public AStarSearch(){
        System.out.println("Starting A* Search");
    }

    //Takes the current board initial or top of queue and makes a copy of it places copy in queue.
    public AStarSearch moveLeft(Node currentBoard){
        //Move blank space left current position of blank space -1
        int freeSpace = currentBoard.getShuffledBoard().indexOf(0);
        if(freeSpace != 0 && freeSpace != 3 && freeSpace !=6){
            //then its valid move
            Node copyOfBoard = new Node(currentBoard);
            int temp = copyOfBoard.getShuffledBoard().get(freeSpace - 1);
            copyOfBoard.getShuffledBoard().set(freeSpace, temp);
            copyOfBoard.getShuffledBoard().set(freeSpace - 1, 0);
            addNewBoard(copyOfBoard);
        }
        return this;
    }

    //Takes the current board initial or top of queue and makes a copy of it places copy in queue.
    public AStarSearch moveRight(Node currentBoard){
        //Move blank space left current position of blank space -1
        int freeSpace = currentBoard.getShuffledBoard().indexOf(0);
        if(freeSpace != 2 && freeSpace != 5 && freeSpace !=8){
            //then its valid move
            Node copyOfBoard = new Node(currentBoard);
            int temp = copyOfBoard.getShuffledBoard().get(freeSpace + 1);
            copyOfBoard.getShuffledBoard().set(freeSpace, temp);
            copyOfBoard.getShuffledBoard().set(freeSpace + 1, 0);
            addNewBoard(copyOfBoard);
        }
        return this;
    }

    //Takes the current board initial or top of queue and makes a copy of it places copy in queue.
    public AStarSearch moveUp(Node currentBoard){
        //Move blank space left current position of blank space -1
        int freeSpace = currentBoard.getShuffledBoard().indexOf(0);
        if(freeSpace > 2){
            //then its valid move
            Node copyOfBoard = new Node(currentBoard);
            int temp = copyOfBoard.getShuffledBoard().get(freeSpace - 3);
            copyOfBoard.getShuffledBoard().set(freeSpace, temp);
            copyOfBoard.getShuffledBoard().set(freeSpace - 3, 0);
            addNewBoard(copyOfBoard);
        }
        return this;
    }

    //Takes the current board initial or top of queue and makes a copy of it places copy in queue.
    public AStarSearch moveDown(Node currentBoard){
        //Move blank space left current position of blank space -1
        int freeSpace = currentBoard.getShuffledBoard().indexOf(0);
        if(freeSpace < 6){
            //then its valid move
            Node copyOfBoard = new Node(currentBoard);
            int temp = copyOfBoard.getShuffledBoard().get(freeSpace + 3);
            copyOfBoard.getShuffledBoard().set(freeSpace, temp);
            copyOfBoard.getShuffledBoard().set(freeSpace + 3, 0);
            addNewBoard(copyOfBoard);
        }
        return this;
    }

    public  AStarSearch isGoalState(Node newBoard){
        if(newBoard.getShuffledBoard().toString().equals("[1, 2, 3, 4, 5, 6, 7, 8, 0]")){
            //found a solution since the hval is 0 it must be equal to the goal
            System.out.println("Found Solution at depth "+ newBoard.getDepth());
            System.out.println("Showing Goal to Initial");
            printGameBoard(newBoard);
            int goalDepth = newBoard.getDepth();
            for(int i=0; i<goalDepth; i++){
                printGameBoard(newBoard.getParent());
                newBoard = newBoard.getParent();
            }
            System.exit(0);
        }
        return this;
    }

    public void addNewBoard(Node newBoard){
        //calculate the hval
        for(int i=0; i<9; i++){
             if(newBoard.getShuffledBoard().get(i) != i+1)
                        newBoard.addOneToHval();
        }

        //see if board is already in open list assume its already there
        boolean boardAlreadyInOList = false;
        Node objectToCheck = null;
        for(Node fromList:this.frontier) {
            if(fromList.getShuffledBoard().toString().equals(newBoard.getShuffledBoard().toString())){
                boardAlreadyInOList = true;
                objectToCheck = fromList;
                break;
            }
        }
        //see if board is already in closed list assume its already there
        boolean boardAlreadyInCList = false;
        for(Node fromList:this.explored) {
            if(fromList.getShuffledBoard().toString().equals(newBoard.getShuffledBoard().toString())){
                boardAlreadyInCList = true;
                break;
            }

        }

        newBoard.setHval(newBoard.getHval() + newBoard.getDepth());
        if(boardAlreadyInOList == false && boardAlreadyInCList == false) {
            //add the board to the queue
            this.frontier.add(newBoard);
        }else if(boardAlreadyInOList == true){
            //check to see if hval is less than the one currently on the board
            if(objectToCheck.getHval() > newBoard.getHval()){
                if(this.frontier.remove(objectToCheck)){
                    this.frontier.add(newBoard);
                }
            }
        }
    }

    //Comparator anonymous class implementation
    public static Comparator<Node> nodeComparator = new Comparator<Node>(){
        @Override
        public int compare(Node n1, Node n2) {
            if (n1.getHval() > n2.getHval())
                return 1;
            if (n1.getHval() < n2.getHval())
                return -1;
            return 0;
        }
    };

    public AStarSearch checkIfSolvable(Node initalNode){
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
