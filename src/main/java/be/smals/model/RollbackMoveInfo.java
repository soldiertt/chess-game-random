package be.smals.model;

import be.smals.model.pieces.ChessPiece;

/**
 * Created by soldiertt on 06-06-14.
 */
public class RollbackMoveInfo {

    private Coordinates previousCoordinates;

    private boolean neverMovedStatus;

    private ChessPiece removedChessPiece;

    private boolean chessPieceWasRemoved;

    private boolean isPromotion;

    public RollbackMoveInfo(Coordinates previousCoordinates, boolean neverMovedStatus) {
        this.previousCoordinates = previousCoordinates;
        this.neverMovedStatus = neverMovedStatus;
        this.chessPieceWasRemoved = false;
    }

    public RollbackMoveInfo(Coordinates previousCoordinates, boolean neverMovedStatus, ChessPiece removedChessPiece) {
        this.previousCoordinates = previousCoordinates;
        this.neverMovedStatus = neverMovedStatus;
        this.removedChessPiece = removedChessPiece;
        this.chessPieceWasRemoved = true;
    }

    public Coordinates getPreviousCoordinates() {
        return previousCoordinates;
    }

    public void setPreviousCoordinates(Coordinates previousCoordinates) {
        this.previousCoordinates = previousCoordinates;
    }

    public boolean isNeverMovedStatus() {
        return neverMovedStatus;
    }

    public void setNeverMovedStatus(boolean neverMovedStatus) {
        this.neverMovedStatus = neverMovedStatus;
    }

    public ChessPiece getRemovedChessPiece() {
        return removedChessPiece;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public void setPromotion(boolean isPromotion) {
        this.isPromotion = isPromotion;
    }

    public void setRemovedChessPiece(ChessPiece removedChessPiece) {
        this.removedChessPiece = removedChessPiece;
    }

    public boolean isChessPieceWasRemoved() {
        return chessPieceWasRemoved;
    }

    public void setChessPieceWasRemoved(boolean chessPieceWasRemoved) {
        this.chessPieceWasRemoved = chessPieceWasRemoved;
    }
}
