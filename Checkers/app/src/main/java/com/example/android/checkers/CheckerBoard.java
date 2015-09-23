package com.example.android.checkers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by emilyandrulis on 9/20/15.
 */
public class CheckerBoard {

    private ArrayList<Checker> mCheckerBoard = new ArrayList<>();

    /**
     * Methods to include:
     *
     * move diagnol right
     * move diagnol left (with up and down parameter)
     * isMovePossible
     * jumpLeft
     * jumpRight
     *
     * Check canKeepJumping
     *      returns the position of possible jump
     *
     * Check to see if one team has won
     *
     * setUpCheckerBoard (startGame)
     *
     * reset method
     *
     */

    /**
     *
     * AI Methods
     *
     * Crude: if can move, then move
     * Less: if can jump, then move first
     */

    public CheckerBoard(){
        mCheckerBoard = new ArrayList<>(64);
    }

    /**
     * Helper method for controller CheckersActivity to easily
     * get the Checker at a specific spot on the board
     * @param position is the spot of the checker on the board
     * @return the checker on that spot
     */

    public Checker getChecker(int position){
        return mCheckerBoard.get(position);
    }

    /**
     * Sets up the checkerboard with the starting game state, i.e.
     * black is on the top of the screen and red is on the bottom.
     * Also, all the checkers play on the red squares. Squares are
     * numbered 0-63 starting at the lower left corner, black square as 0.
     *
     */
    public void startGameState(){

        ArrayList<Integer> checkeredSpaces =  new ArrayList<Integer>(Arrays.asList(1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21,
                23, 24, 26, 28, 30, 33, 35, 37, 39, 41, 42, 44, 46, 48,
                51, 53, 55, 56, 58, 60, 62));



        for(int i =0; i < 63; i++){
            if(!checkeredSpaces.contains(i)){
                //set all black spaces to null on the board
                mCheckerBoard.add(null);
            }
            else if(i < 24){
                //set first three rows to red checkers
                mCheckerBoard.add(new Checker((i/2), true));
            }
            else if(i < 40){
                //set middle two rows to null
                mCheckerBoard.add(null);
            }
            else{
                //set top three row to black checkers
                mCheckerBoard.add(new Checker((i/2), false));
            }
        }

    }


    /**
     * Finds out if there is a winner by going through all the checkers
     * and making sure they are all the same color as the winning team
     *
     * @param isRed is true if winning team is red, false if winner is black
     * @return whether or not that color has won
     */
    public boolean isWinner(boolean isRed){

        for(int i =0; i<mCheckerBoard.size(); i++){
            if(mCheckerBoard.get(i)!=null  &&  mCheckerBoard.get(i).isRed() != isRed){
                return false;
            }
        }
        return true;
    }

    /**
     * This method moves the checker at the start position one square
     * diagonally to the right, either up or down based on the second parameter.
     * This method does NOT jump, and will not remove any checkers from the board.
     * If moved into the first or last row appropriately, it will king the checker.
     *
     * @return A boolean telling if the event happened (true should move checker, false does nothing)
     * @param startPosition is current position of the checker to be moved
     * @param moveUp is true when checker is moving up the board
     * @param current is the Checker that is trying to move
     */

    public int[] moveDiagonalRight(int startPosition, boolean moveUp, Checker current){

        int endPosition = 0;
        if(moveUp)
        {
            //moving up diagonal right is +9,
            endPosition = startPosition + 9;
        }
        else
        {
            //moving down diagonal right is -7,
            endPosition = startPosition - 7;
        }

        if(isMovePossible(startPosition, endPosition, false))
        {
            //want to move checker from current position to new one
            mCheckerBoard.set(startPosition, null);
            mCheckerBoard.set(endPosition, current);
            int[] affectedSpaces = new int[] {startPosition, endPosition};
            return affectedSpaces;
        }
        else
        {
            int [] noChange = new int[0];
            return noChange;
        }

    }

    /**
     * This method moves the checker at the start position one square
     * diagonally to the left, either up or down based on the second parameter.
     * This method does NOT jump, and will not remove any checkers from the board.
     * If moved into the first or last row appropriately, it will king the checker.
     *
     * @return A boolean telling if the event happened (true should move checker, false does nothing)
     * @param startPosition is current position of the checker to be moved
     * @param moveUp is true when checker is moving up the board
     * @param current is the Checker that is trying to move
     */

    public int[] moveDiagonalLeft(int startPosition, boolean moveUp, Checker current){

        int endPosition = 0;
        if(moveUp)
        {
            //moving up diagonal left is +7,
            endPosition = startPosition + 7;
        }
        else
        {
            //moving down diagonal left is -9,
            endPosition = startPosition - 9;
        }

        if(isMovePossible(startPosition, endPosition, false))
        {
            //want to move checker from current position to new one
            mCheckerBoard.set(startPosition, null);
            mCheckerBoard.set(endPosition, current);
            int[] affectedSpaces = new int[] {startPosition, endPosition};
            return affectedSpaces;
        }
        else
        {
            int [] noChange = new int[0];
            return noChange;
        }

    }

    /**
     *
     * This method moves the checker from the start position, jumping over another piece
     * diagonally to the right, and then ends on its own unoccupied space. The jumped piece
     * is removed from play and taken out of the array permentantly.
     *
     * @param startPosition
     * @param moveUp
     * @param current
     * @return
     */

    public int[] jumpRight(int startPosition, boolean moveUp, Checker current)
    {
        int endPosition = 0;
        int jumpPosition = 0;
        if(moveUp)
        {
            endPosition = startPosition + 18;
            jumpPosition = startPosition + 9;
        }
        else {
            endPosition = startPosition - 14;
            jumpPosition = startPosition - 7;
        }

        if(isMovePossible(startPosition, endPosition, false))
        {
            mCheckerBoard.set(startPosition, null);
            mCheckerBoard.set(jumpPosition, null);
            mCheckerBoard.set(endPosition, current);
            int[] affectedSpaces = new int[] {startPosition, jumpPosition, endPosition};
            return affectedSpaces;
        }
        else {
            int [] noChange = new int[0];
            return noChange;
        }
    }

    /**
     *
     * This method moves the checker from the start position, jumping over another piece
     * diagonally to the left, and then ends on its own unoccupied space. The jumped piece
     * is removed from play and taken out of the array permentantly.
     *
     * @param startPosition
     * @param moveUp
     * @param current
     * @return
     */

    public int[] jumpLeft(int startPosition, boolean moveUp, Checker current)
    {
        int endPosition = 0;
        int jumpPosition = 0;
        if(moveUp)
        {
            endPosition = startPosition + 14;
            jumpPosition = startPosition + 7;
        }
        else {
            endPosition = startPosition - 18;
            jumpPosition = startPosition - 9;
        }

        if(isMovePossible(startPosition, endPosition, false))
        {
            mCheckerBoard.set(startPosition, null);
            mCheckerBoard.set(jumpPosition, null);
            mCheckerBoard.set(endPosition, current);
            int[] affectedSpaces = new int[] {startPosition, jumpPosition, endPosition};
            return affectedSpaces;
        }
        else {
            int [] noChange = new int[0];
            return noChange;
        }
    }

    /**
     * This method does all the checks to see if the proposed move is possible.
     * Checks that end position is on the board, and that it is an appropriate column.
     * Also checks that there is no checker on the end square already.
     * @param start is the starting position of the checker
     * @param end is the proposed ending position of the checker
     * @param isRed is true if the checkers color is red, false otherwise
     * @return true if move is deemed valid, false otherwise
     */

    public boolean isMovePossible(int start, int end, boolean isRed){

        int moveDiff = end - start; //moveDiff tracks the movement made based on difference btwn start and end
        //general check for if a checker is on the end square
        if (mCheckerBoard.get(end) != null)
        {
            return false;   //can't move b/c a checker is on the end square
        }

        //general checks that start and end are on the board
        if(end<0 || end>63)
        {
            return false; //can't move b/c end is off-board
        }


        //checks for diagonalRight
        if(moveDiff == 9 || moveDiff ==-7)
        {
            if(start % 8 == 7){
                return false; //can't move b/c off-board on right
            }
        }

        //checks for diagonalLeft
        if(moveDiff == 7 || moveDiff ==-9)
        {
            if(start % 8 == 0){
                return false; //can't move b/c off-board on left
            }
        }

        //checks for jumpRight
        if(moveDiff == 18 || moveDiff == -14){
            //column check
            if((start % 8 == 7) || (start % 8 == 6)){
                return false; //can't move b/c off-board on right
            }
            //need to check if there is a piece of opposite color in between
            int jumpSpace = start + (moveDiff/2);
            Checker jumped = mCheckerBoard.get(jumpSpace);
            if(jumped == null)
            {
                return false; //can't move b/c no checker to jump
            }
            else if (jumped.isRed() == isRed)
            {
                return false; //can't move b/c can't jump own color
            }
        }

        //checks for jumpLeft
        if(moveDiff == 14 || moveDiff == -18){
            if((start % 8 == 7) || (start % 8 == 6)) {
                return false; //can't move b/c off-board on right
            }
            //need to check if there is a piece of opposite color in between
            int jumpSpace = start + (moveDiff/2);
            Checker jumped = mCheckerBoard.get(jumpSpace);
            if(jumped == null)
            {
                return false; //can't move b/c no checker to jump
            }
            else if (jumped.isRed() == isRed)
            {
                return false; //can't move b/c can't jump own color
            }
        }

        if(moveDiff == 7 || moveDiff ==-7 || moveDiff ==9 || moveDiff ==-9
                || moveDiff == 18 || moveDiff == -18 || moveDiff == 14 || moveDiff ==-14){
            return true;
        }
        else{
            return false;
        }

    }


    /**
     *
     * AI Methods
     *
     * Crude: if can move, then move
     * Less: if can jump, then move first
     *
     * assumes AI is black, so isRed is false
     */

    public int[] findMove(){
        int[] blackCheckerSpots = new int[12];
        int i =0;
        while(mCheckerBoard.indexOf(new Checker(1, false)) != -1){
            int startPosition = mCheckerBoard.indexOf(new Checker(1, false));    //need to override Checker equals method
            Checker current = mCheckerBoard.get(startPosition);
            int[] answer = jumpRight(startPosition, false, current);
            if(answer.length > 1)
            {
                //jumped right downwards
                return answer;
            }
            answer = jumpLeft(startPosition, false, current);
            if(answer.length > 1){
                //jumped left downwards
                return answer;
            }
            answer = moveDiagonalRight(startPosition, false, current);
            if(answer.length > 1){
                //moved diagonal right downwards
                return;
            }
            answer = moveDiagonalLeft(startPosition, false, current);
            if(answer.length > 1){
                //moved diagonal left downwards
                return;
            }

            //end of loop
            //these are the ones that need to be set back to black at the end
            current.setIsRed(true);
            blackCheckerSpots[i]=startPosition;
            i++;
        }

        for(int j =0; j<blackCheckerSpots.length; j++){
            Checker changed = mCheckerBoard.get(blackCheckerSpots[j]);
            changed.setIsRed(false);
            mCheckerBoard.set(blackCheckerSpots[j], changed);
        }

    }



}
