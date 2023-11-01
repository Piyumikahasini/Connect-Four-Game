package lk.ijse.dep.service;

public class HumanPlayer extends Player {
    public HumanPlayer(Board board) {
        super(board);
    }
    @Override
    public void movePiece(int col){
        try {
            boolean tre = board.isLegalMove(col);

            if (tre = true) {
                board.updateMove(col, Piece.BLUE);
                board.getBoardUI().update(col, true);
                Winner winner = board.findWinner();
                if (winner.getWinningPiece() == Piece.BLUE) {
                    board.getBoardUI().notifyWinner(winner);
                }
                if (board.existLegalMoves() == false) {
                    board.getBoardUI().notifyWinner(winner);
                }
            }
        }catch(Exception e){}
    }
}
