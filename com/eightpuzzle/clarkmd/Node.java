package com.eightpuzzle.clarkmd;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michael Clark.
 */
public class Node {
    private ArrayList<Integer> gameBoard;
    private Node parent;
    private int depth = 0;
    private int hval = 0;

    //constructor
    public Node(){
        //Make a new board
        this.gameBoard = new ArrayList<Integer>();
        
        for(int i=0; i<9; i++){
            this.gameBoard.add(i);
        }
        
        Collections.shuffle(this.gameBoard);
    }

    //copy constructor
    public Node(Node currentBoard){
        this.gameBoard = new ArrayList<Integer>();
        this.gameBoard.addAll(currentBoard.gameBoard);
        this.depth = currentBoard.depth + 1;
        this.parent = currentBoard;
    }

    public ArrayList<Integer> getShuffledBoard(){
        //returned shuffled board
        return this.gameBoard;
    }

    public int getDepth(){
        return this.depth;
    }

    public Node getParent(){
        return this.parent;
    }

    public void addOneToHval(){
        this.hval = this.hval + 1;
    }

    public int getHval(){
        return this.hval;
    }

    public void setHval(int hval){
        this.hval = hval;
    }
}
