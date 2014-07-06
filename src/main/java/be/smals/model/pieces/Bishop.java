package be.smals.model.pieces;

import be.smals.model.ChessBoard;
import be.smals.model.Color;
import be.smals.model.Coordinates;
import be.smals.util.CoordUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soldiertt on 07-06-14.
 */
public class Bishop extends ChessPiece {

    public Bishop(ChessBoard board, Coordinates coordinates, Color color) {
        super(board, coordinates, color);
        setLetter('B');
    }

    @Override
    public List<Coordinates> availableMovesWithoutAttack() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        List<Coordinates> moves = new ArrayList<Coordinates>();
        Coordinates forwardLeft = CoordUtil.forwardObliqueLeft(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(forwardLeft)) {
            availables.add(forwardLeft);
            forwardLeft = CoordUtil.forwardObliqueLeft(forwardLeft, 1, this.getColor());
        }
        Coordinates forwardRight = CoordUtil.forwardObliqueRight(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(forwardRight)) {
            availables.add(forwardRight);
            forwardRight = CoordUtil.forwardObliqueRight(forwardRight, 1, this.getColor());
        }
        Coordinates backwardLeft = CoordUtil.backwardObliqueLeft(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(backwardLeft)) {
            availables.add(backwardLeft);
            backwardLeft = CoordUtil.backwardObliqueLeft(backwardLeft, 1, this.getColor());
        }
        Coordinates backwardRight = CoordUtil.backwardObliqueRight(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(backwardRight)) {
            availables.add(backwardRight);
            backwardRight = CoordUtil.backwardObliqueRight(backwardRight, 1, this.getColor());
        }
        return availables;
    }

    @Override
    public List<Coordinates> availableAttacks() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        List<Coordinates> moves = new ArrayList<Coordinates>();
        Coordinates forwardLeft = CoordUtil.forwardObliqueLeft(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(forwardLeft)) {
            forwardLeft = CoordUtil.forwardObliqueLeft(forwardLeft, 1, this.getColor());
        }
        if (this.getChessBoard().coordContainsOpponent(forwardLeft, this.getColor())) {
            availables.add(forwardLeft);
        }
        Coordinates forwardRight = CoordUtil.forwardObliqueRight(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(forwardRight)) {
            forwardRight = CoordUtil.forwardObliqueRight(forwardRight, 1, this.getColor());
        }
        if (this.getChessBoard().coordContainsOpponent(forwardRight, this.getColor())) {
            availables.add(forwardRight);
        }
        Coordinates backwardLeft = CoordUtil.backwardObliqueLeft(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(backwardLeft)) {
            backwardLeft = CoordUtil.backwardObliqueLeft(backwardLeft, 1, this.getColor());
        }
        if (this.getChessBoard().coordContainsOpponent(backwardLeft, this.getColor())) {
            availables.add(backwardLeft);
        }
        Coordinates backwardRight = CoordUtil.backwardObliqueRight(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(backwardRight)) {
            backwardRight = CoordUtil.backwardObliqueRight(backwardRight, 1, this.getColor());
        }
        if (this.getChessBoard().coordContainsOpponent(backwardRight, this.getColor())) {
            availables.add(backwardRight);
        }

        return availables;
    }
}
