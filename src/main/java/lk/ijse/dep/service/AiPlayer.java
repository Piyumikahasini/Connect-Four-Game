package lk.ijse.dep.service;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class AiPlayer extends Player {

    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        int bestScore = (int) Double.NEGATIVE_INFINITY;
        System.out.println(bestScore + " - old best score");
        System.out.println("////////////////////////////////");
        for (int i = 0; i < 6; i++) {

            if (board.isLegalMove(i)) {

                int row = board.findNextAvailableSpot(i);
                board.updateMove(i, Piece.GREEN);
                int heuristicVal = minimax(0, false);
                System.out.println("col " + i + "- " + heuristicVal);
                board.updateMove(i, row, Piece.EMPTY);
                if (heuristicVal > bestScore) {
                    bestScore = heuristicVal;
                    col = i;
                }
                System.out.println(bestScore + " - new best score");
            }
        }
        System.out.println("////////////////////////////////");
        System.out.println("col num :" + col);
        if (board.isLegalMove(col)) {
            board.updateMove(col, Piece.GREEN);
            board.getBoardUI().update(col, false);
            Winner winner = board.findWinner();
            if (winner.getWinningPiece().equals(Piece.GREEN)) {
                board.getBoardUI().notifyWinner(winner);
            } else {
                if (board.existLegalMoves() == false) {
                    board.getBoardUI().notifyWinner(winner);
                }
            }
        }
    }

    private int minimax(int depth, boolean maximisingPlayer) {
        Winner winner = board.findWinner();
        if (depth == 4 && board.existLegalMoves() == true) {

            if (winner.getWinningPiece().equals(Piece.EMPTY)) {
                return 0;
            }
            if (winner.getWinningPiece().equals(Piece.GREEN)) {
                return +100;
            }
            if (winner.getWinningPiece().equals(Piece.BLUE)) {
                return -100;
            }
        }

        int row;

        if (maximisingPlayer) {
            int maxEval = (int) Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 6; i++) {

                if (board.isLegalMove(i)) {
                    System.out.println("max");
                    row = board.findNextAvailableSpot(i);
                    board.updateMove(i, Piece.GREEN);
                    int heuristicVal = minimax(depth + 1, false);
                    board.updateMove(i, row, Piece.EMPTY);
                    maxEval = Math.max(maxEval, heuristicVal);
                }

            }
            return maxEval;
        } else {
            int minEval = (int) Double.POSITIVE_INFINITY;
            for (int i = 0; i < 6; i++) {

                if (board.isLegalMove(i)) {
                    System.out.println("min");
                    row = board.findNextAvailableSpot(i);
                    board.updateMove(i, Piece.BLUE);
                    int heuristicVal = minimax(depth + 1, true);
                    System.out.println("min heuristical :" + heuristicVal);
                    board.updateMove(i, row, Piece.EMPTY);
                    minEval = Math.min(minEval, heuristicVal);
                }

            }
            return minEval;
        }

    }
}

