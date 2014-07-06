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
public class Knight extends ChessPiece {

    public Knight(ChessBoard board, Coordinates coordinates, Color color) {
        super(board, coordinates, color);
        setLetter('N');
    }

    @Override
    public List<Coordinates> availableMovesWithoutAttack() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        List<Coordinates> coords = CoordUtil.knightMoves(start);
        for (Coordinates coord : coords) {
            if(this.getChessBoard().coordIsValidAndEmpty(coord)) {
                availables.add(coord);
            }
        }
        return availables;
    }

    @Override
    public List<Coordinates> availableAttacks() {
        List<Coordinates> availables = new ArrayList<Coordinates>();
        Coordinates start = this.getCoordinates();
        List<Coordinates> coords = CoordUtil.knightMoves(start);
        for (Coordinates coord : coords) {
            if(this.getChessBoard().coordContainsOpponent(coord, this.getColor())) {
                availables.add(coord);
            }
        }

        return availables;
    }
}
