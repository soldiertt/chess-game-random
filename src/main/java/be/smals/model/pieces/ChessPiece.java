package be.smals.model.pieces;

import be.smals.model.ChessBoard;
import be.smals.model.Color;
import be.smals.model.Coordinates;
import be.smals.model.RollbackMoveInfo;

import java.util.List;

/**
 * Created by soldiertt on 05-06-14.
 */
public abstract class ChessPiece {

    private ChessBoard chessBoard;

    private Coordinates coordinates;

    private Color color;

    private char letter;

    private boolean neverMoved;

    protected ChessPiece(ChessBoard board, Coordinates coordinates, Color color) {
        this.chessBoard = board;
        this.coordinates = coordinates;
        this.color = color;
        this.neverMoved = true;
    }

    public void move(Coordinates coords) {
        this.coordinates.setX(coords.getX());
        this.coordinates.setY(coords.getY());
        this.neverMoved = false;
    }

    public void attack(Coordinates coords) {
        ChessPiece attackedPiece = chessBoard.locate(coords);
        attackedPiece.remove();
        this.coordinates.setX(coords.getX());
        this.coordinates.setY(coords.getY());
        this.neverMoved = false;
    }

    public void remove() {
        chessBoard.removePiece(this);
    }

    public abstract List<Coordinates> availableMovesWithoutAttack();

    public abstract List<Coordinates> availableAttacks();

    public boolean canAttack(Coordinates coords) {
        List<Coordinates> attackCoords = availableAttacks();
        for (Coordinates attackCoord : attackCoords) {
            if (attackCoord.equals(coords)) {
                return true;
            }
        }
        return false;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isNeverMoved() {
        return neverMoved;
    }

    public void setNeverMoved(boolean neverMoved) {
        this.neverMoved = neverMoved;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return Character.toString(getLetter()) + coordinates;
    }
}
