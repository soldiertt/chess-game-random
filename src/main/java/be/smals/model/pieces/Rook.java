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
public class Rook extends ChessPiece {

    public Rook(ChessBoard board, Coordinates coordinates, Color color) {
        super(board, coordinates, color);
        setLetter('R');
    }

    @Override
    public List<Coordinates> availableMovesWithoutAttack() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        List<Coordinates> moves = new ArrayList<Coordinates>();
        Coordinates forward = CoordUtil.forward(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(forward)) {
            availables.add(forward);
            forward = CoordUtil.forward(forward, 1, this.getColor());
        }
        Coordinates backward = CoordUtil.backward(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(backward)) {
            availables.add(backward);
            backward = CoordUtil.backward(backward, 1, this.getColor());
        }
        Coordinates left = CoordUtil.left(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(left)) {
            availables.add(left);
            left = CoordUtil.left(left, 1, this.getColor());
        }
        Coordinates right = CoordUtil.right(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(right)) {
            availables.add(right);
            right = CoordUtil.right(right, 1, this.getColor());
        }
        return availables;
    }

    @Override
    public List<Coordinates> availableAttacks() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        List<Coordinates> moves = new ArrayList<Coordinates>();
        Coordinates forward = CoordUtil.forward(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(forward)) {
            forward = CoordUtil.forward(forward, 1, this.getColor());
        }
        if (this.getChessBoard().coordContainsOpponent(forward, this.getColor())) {
            availables.add(forward);
        }
        Coordinates backward = CoordUtil.backward(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(backward)) {
            backward = CoordUtil.backward(backward, 1, this.getColor());
        }
        if (this.getChessBoard().coordContainsOpponent(backward, this.getColor())) {
            availables.add(backward);
        }
        Coordinates left = CoordUtil.left(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(left)) {
            left = CoordUtil.left(left, 1, this.getColor());
        }
        if (this.getChessBoard().coordContainsOpponent(left, this.getColor())) {
            availables.add(left);
        }
        Coordinates right = CoordUtil.right(start, 1, this.getColor());
        while (this.getChessBoard().coordIsValidAndEmpty(right)) {
            right = CoordUtil.right(right, 1, this.getColor());
        }
        if (this.getChessBoard().coordContainsOpponent(right, this.getColor())) {
            availables.add(right);
        }
        return availables;
    }
}
