package be.smals.model.pieces;

import be.smals.model.ChessBoard;
import be.smals.model.Color;
import be.smals.model.Coordinates;
import be.smals.util.CoordUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soldiertt on 06-06-14.
 */
public class King extends ChessPiece {

    public King(ChessBoard board, Coordinates coordinates, Color color) {
        super(board, coordinates, color);
        setLetter('K');
    }

    @Override
    public List<Coordinates> availableMovesWithoutAttack() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        List<Coordinates> moves = new ArrayList<Coordinates>();
        moves.add(CoordUtil.forward(start, 1, this.getColor()));
        moves.add(CoordUtil.forwardObliqueRight(start, 1, this.getColor()));
        moves.add(CoordUtil.right(start, 1, this.getColor()));
        moves.add(CoordUtil.backwardObliqueRight(start, 1, this.getColor()));
        moves.add(CoordUtil.backward(start, 1, this.getColor()));
        moves.add(CoordUtil.backwardObliqueLeft(start, 1, this.getColor()));
        moves.add(CoordUtil.left(start, 1, this.getColor()));
        moves.add(CoordUtil.forwardObliqueLeft(start, 1, this.getColor()));

        for (Coordinates coord : moves) {
            if (this.getChessBoard().coordIsValidAndEmpty(coord)) {
                availables.add(coord);
            }
        }
        return availables;
    }

    @Override
    public List<Coordinates> availableAttacks() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        List<Coordinates> moves = new ArrayList<Coordinates>();
        moves.add(CoordUtil.forward(start, 1, this.getColor()));
        moves.add(CoordUtil.forwardObliqueRight(start, 1, this.getColor()));
        moves.add(CoordUtil.right(start, 1, this.getColor()));
        moves.add(CoordUtil.backwardObliqueRight(start, 1, this.getColor()));
        moves.add(CoordUtil.backward(start, 1, this.getColor()));
        moves.add(CoordUtil.backwardObliqueLeft(start, 1, this.getColor()));
        moves.add(CoordUtil.left(start, 1, this.getColor()));
        moves.add(CoordUtil.forwardObliqueLeft(start, 1, this.getColor()));

        for (Coordinates coord : moves) {
            if (this.getChessBoard().coordContainsOpponent(coord, this.getColor())) {
                availables.add(coord);
            }
        }
        return availables;
    }
}
