package be.smals.model;

import be.smals.model.pieces.ChessPiece;
import be.smals.model.pieces.Pawn;
import be.smals.model.pieces.Queen;
import be.smals.util.CoordUtil;

/**
 * Created by soldiertt on 05-06-14.
 */
public class Move {

    ChessPiece piece;

    Coordinates from;

    Coordinates destination;

    boolean isAttack;

    private char promotion = ' ';

    private RollbackMoveInfo rollbackMoveInfo;

    public Move(ChessPiece piece, Coordinates destination, boolean isAttack) {
        this.from = new Coordinates(piece.getCoordinates().getX(), piece.getCoordinates().getY());
        this.piece = piece;
        this.destination = destination;
        this.isAttack = isAttack;
    }

    public void run() {
        if (isAttack) {
            ChessPiece attackedPiece;
            if (piece.getChessBoard().coordIsValidAndEmpty(destination)) { //Prise en passant
                attackedPiece = piece.getChessBoard().locate(CoordUtil.backward(destination, 1, piece.getColor()));
            } else {
                attackedPiece = piece.getChessBoard().locate(destination);
            }
            this.rollbackMoveInfo = new RollbackMoveInfo(new Coordinates(piece.getCoordinates().getX(), piece.getCoordinates().getY()), piece.isNeverMoved(), attackedPiece);
            piece.attack(destination);
        } else {
            this.rollbackMoveInfo = new RollbackMoveInfo(new Coordinates(piece.getCoordinates().getX(), piece.getCoordinates().getY()), piece.isNeverMoved());
            piece.move(destination);
        }
        if (getPiece().getClass().equals(Pawn.class)
                && ((piece.getColor().equals(Color.WHITE) && destination.getY() == 8)
                            || (piece.getColor().equals(Color.BLACK) && destination.getY() == 1))) {
                //Promotion
                this.promotion = 'Q';
                // Add queen
                piece.getChessBoard().addPiece(new Queen(piece.getChessBoard(), piece.getCoordinates(), piece.getColor()));
                //Remove pawn
                piece.remove();
                this.rollbackMoveInfo.setPromotion(true);
        }
    }

    public void rollback() {
        if (rollbackMoveInfo.isChessPieceWasRemoved()) {
            //Restore removed piece
            piece.getChessBoard().addPiece(rollbackMoveInfo.getRemovedChessPiece());
        }
        piece.setNeverMoved(rollbackMoveInfo.isNeverMovedStatus());
        if (rollbackMoveInfo.isPromotion()) {
            //Add the pawn back
            //piece.getChessBoard().addPiece(new Pawn(piece.getChessBoard(), rollbackMoveInfo.getPreviousCoordinates(), piece.getColor()));
            piece.setCoordinates(rollbackMoveInfo.getPreviousCoordinates());
            piece.getChessBoard().addPiece(piece);

            //Remove the Queen (or other promoted piece)
            ChessPiece pieceToRemove = piece.getChessBoard().locate(destination);
            pieceToRemove.remove();
        } else {
            piece.setCoordinates(rollbackMoveInfo.getPreviousCoordinates());
        }

    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public Coordinates getFrom() {
        return from;
    }

    public Coordinates getDestination() {
        return destination;
    }

    public void setDestination(Coordinates destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        if (!isAttack) {
            if (this.getPiece().getLetter() != ' ') {
                return Character.toString(this.getPiece().getLetter()) + getFrom().toString() + this.getDestination();
            } else {
                return this.getDestination().toString() + (promotion != ' ' ? promotion : "");
            }
        } else {
            if (this.getPiece().getLetter() == ' ') {
                return getFrom().toString() +  "x" + this.getDestination() + (promotion != ' ' ? promotion : "");
            } else {
                return this.getPiece().getLetter() + getFrom().toString() +  "x" + this.getDestination();
            }

        }
    }
}
