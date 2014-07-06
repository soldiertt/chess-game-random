package be.smals.model;

import be.smals.exception.ChessGameException;
import be.smals.model.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by soldiertt on 05-06-14.
 */
public class ChessGame {

    private List<Move> movesHistory = new ArrayList<Move>();
    private ChessBoard board;

    public ChessGame() {
        board = new ChessBoard(this);
        board.resetPosition();
    }

    public void move(Color color) throws ChessGameException {
        List<Move> allMoves = board.availableMoves(color);
        int size = allMoves.size();
        if (size > 0) {
            Random r = new Random();
            int chooseMove = 0 + r.nextInt(size);
            Move choosenMove = allMoves.get(chooseMove);
            choosenMove.run();
            movesHistory.add(choosenMove);

            if (movesHistory.size() > 400) {
                throw new ChessGameException("Too many moves !");
            }
        } else {
            //Is any King in check ?
            if (board.isMyKingInCheck(Color.WHITE)) {
                throw new ChessGameException("BLACK WINS !");
            } else if (board.isMyKingInCheck(Color.BLACK)) {
                throw new ChessGameException("WHITE WINS !");
            } else {
                throw new ChessGameException("DRAW - King is PAT !");
            }

        }
    }

    public boolean lastMoveIsPawnTwoCasesAt(Coordinates coords) {
        Move lastMove = movesHistory.get(movesHistory.size() - 1);
        if (lastMove.getPiece().getClass().equals(Pawn.class) && lastMove.getDestination().equals(coords)) {
            int yDiff = lastMove.getFrom().getY() - lastMove.getDestination().getY();
            return Math.abs(yDiff) == 2;
        } else {
            return false;
        }
    }

    public List<Move> getMovesHistory() {
        return movesHistory;
    }
}
