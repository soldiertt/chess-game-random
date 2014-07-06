package be.smals.model.pieces;

import be.smals.model.ChessBoard;
import be.smals.model.Color;
import be.smals.model.Coordinates;
import be.smals.model.RollbackMoveInfo;
import be.smals.util.CoordUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soldiertt on 05-06-14.
 */
public class Pawn extends ChessPiece {

    public Pawn(ChessBoard board, Coordinates coordinates, Color color) {
        super(board, coordinates, color);
        setLetter(' ');
    }

    @Override
    public void attack(Coordinates coords) {
        Coordinates whereToRemove = coords; //Normal case
        if (getChessBoard().coordIsValidAndEmpty(coords)) { //Prise en passant
            whereToRemove = CoordUtil.backward(coords, 1, getColor());
        }
        ChessPiece attackedPiece = getChessBoard().locate(whereToRemove);
        attackedPiece.remove();

        getCoordinates().setX(coords.getX());
        getCoordinates().setY(coords.getY());
        setNeverMoved(false);
    }

    @Override
    public List<Coordinates> availableMovesWithoutAttack() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        Coordinates oneStepFront = CoordUtil.forward(start, 1, this.getColor());
        if (this.getChessBoard().coordIsValidAndEmpty(oneStepFront)) {
            availables.add(oneStepFront);
            if (this.isNeverMoved()) {
                Coordinates twoStepFront = CoordUtil.forward(start, 2, this.getColor());
                if (this.getChessBoard().coordIsValidAndEmpty(twoStepFront)) {
                    availables.add(twoStepFront);
                }
            }
        }
        return availables;
    }

    @Override
    public List<Coordinates> availableAttacks() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        Coordinates atObliqueLeft = CoordUtil.forwardObliqueLeft(start, 1, this.getColor());
        if (this.getChessBoard().coordContainsOpponent(atObliqueLeft, this.getColor())) {
            availables.add(atObliqueLeft);
        }
        Coordinates atObliqueRight = CoordUtil.forwardObliqueRight(start, 1, this.getColor());
        if (this.getChessBoard().coordContainsOpponent(atObliqueRight, this.getColor())) {
            availables.add(atObliqueRight);
        }
        Coordinates atLeft = CoordUtil.left(start, 1, this.getColor());
        if (this.getChessBoard().coordContainsOpponent(atLeft, this.getColor(), Pawn.class)) {
            if (getChessBoard().getGame().lastMoveIsPawnTwoCasesAt(atLeft)) {
               //availables.add(atLeft);
                availables.add(atObliqueLeft);
            }
        }
        Coordinates atRight = CoordUtil.right(start, 1, this.getColor());
        if (this.getChessBoard().coordContainsOpponent(atRight, this.getColor(), Pawn.class)) {
            if (getChessBoard().getGame().lastMoveIsPawnTwoCasesAt(atRight)) {
                //availables.add(atRight);
                availables.add(atObliqueRight);
            }
        }
        return availables;
    }

}
