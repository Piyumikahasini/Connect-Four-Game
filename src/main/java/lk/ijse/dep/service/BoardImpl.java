package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;
import lk.ijse.dep.service.Board;

public class BoardImpl implements Board {
    private BoardUI boardUI;
    Piece[][] pieces;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }


    }


    @Override
    public BoardUI getBoardUI() {

        System.out.println(pieces[0][0]);
        System.out.println(pieces[0][1]);
        System.out.println(pieces[0][2]);
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {  // check next empty row
        int count = -1;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                count = i;
                break;
            } else {
                count = -1;
            }
        }

        return count;

    }

    @Override
    public boolean isLegalMove(int col) { // Check empty spot and return true , false
        int count = findNextAvailableSpot(col);
        if (count == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean existLegalMoves() { //check all the table empty spot
        boolean tru = false;
        for (int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                if(pieces[i][j] == Piece.EMPTY){
                    tru = true;
                    break;
                }
            }
        }
  return tru;
    }

    @Override
    public void updateMove(int col, Piece move) {   //set colour and count array
        int count = findNextAvailableSpot(col);
        pieces[col][count] = move;
    }

    @Override
    public void updateMove(int col,int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {

        int col1 = -1;
        int row1 = -1;
        int col2 = -1;
        int row2 = -1;
        Winner winner = null;
        int bluecount=0;
        int greencount=0;
        for (int i=0; i<6; i++){
            bluecount=0;
            greencount=0;
            for(int j=0; j<5; j++){
                if(pieces[i][j] == Piece.BLUE){
                    bluecount++;
                    if(bluecount==1){
                        col1 = i;
                        row1 = j;
                    }
                    if(bluecount==4){
                        col2 = i;
                        row2 = j;
                        winner = new Winner(Piece.BLUE,col1,row1,col2,row2);
                    }
                }else{
                    bluecount=0;
                }
                if(pieces[i][j] == Piece.GREEN){
                    greencount++;
                    if(greencount==1){
                        col1 = i;
                        row1 = j;
                    }
                    if(greencount==4){
                        col2 = i;
                        row2 = j;
                        winner = new Winner(Piece.GREEN,col1,row1,col2,row2);
                    }
                }else{
                    greencount=0;
                }
            }
        }

        for (int i=0; i<5; i++){
            bluecount=0;
            greencount=0;
            for(int j=0; j<6; j++){
                if(pieces[j][i] == Piece.BLUE){
                    bluecount++;
                    if(bluecount==1){
                        col1 = j;
                        row1 = i;
                    }
                    if(bluecount==4){
                        col2 = j;
                        row2 = i;
                        winner = new Winner(Piece.BLUE,col1,row1,col2,row2);
                    }
                }else{
                    bluecount=0;
                }
                if(pieces[j][i] == Piece.GREEN){
                    greencount++;
                    if(greencount==1){
                        col1 = j;
                        row1 = i;
                    }
                    if(greencount==4){
                        col2 = j;
                        row2 = i;
                        winner = new Winner(Piece.GREEN,col1,row1,col2,row2);
                    }
                }else{
                    greencount=0;
                }
            }
        }

        if(winner == null){
            winner = new Winner(Piece.EMPTY);
        }
        return winner;
    }
}