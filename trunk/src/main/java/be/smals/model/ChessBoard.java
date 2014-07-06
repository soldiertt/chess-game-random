package be.smals.model;

import be.smals.model.pieces.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by soldiertt on 05-06-14.
 */
public class ChessBoard {

    private static final int MAX_X = 8;
    private static final int MAX_Y = 8;

    private ChessGame game;

    private List<ChessPiece> chessPieceList = new ArrayList<ChessPiece>();

    public ChessBoard(ChessGame game) {
        this.game = game;
    }

    public void resetPosition() {
        /** WHITE PIECES **/
        //Add 8 pawn
        for (int x=1; x <= MAX_X; x++) {
            chessPieceList.add(new Pawn(this, new Coordinates(x, 2), Color.WHITE));
        }
        //Add 2 rooks
        chessPieceList.add(new Rook(this, new Coordinates(1, 1), Color.WHITE));
        chessPieceList.add(new Rook(this, new Coordinates(8, 1), Color.WHITE));
        //Add 2 bishops
        chessPieceList.add(new Bishop(this, new Coordinates(3, 1), Color.WHITE));
        chessPieceList.add(new Bishop(this, new Coordinates(6, 1), Color.WHITE));
        //Add 2 knights
        chessPieceList.add(new Knight(this, new Coordinates(2, 1), Color.WHITE));
        chessPieceList.add(new Knight(this, new Coordinates(7, 1), Color.WHITE));
        //Add a king
        chessPieceList.add(new King(this, new Coordinates(5, 1), Color.WHITE));
        //Add a queen
        chessPieceList.add(new Queen(this, new Coordinates(4, 1), Color.WHITE));

        /** BLACK PIECES **/
        //Add 8 pawn
        for (int x=1; x <= MAX_X; x++) {
            chessPieceList.add(new Pawn(this, new Coordinates(x, 7), Color.BLACK));
        }
        //Add 2 rooks
        chessPieceList.add(new Rook(this, new Coordinates(1, 8), Color.BLACK));
        chessPieceList.add(new Rook(this, new Coordinates(8, 8), Color.BLACK));
        //Add 2 bishops
        chessPieceList.add(new Bishop(this, new Coordinates(3, 8), Color.BLACK));
        chessPieceList.add(new Bishop(this, new Coordinates(6, 8), Color.BLACK));
        //Add 2 knights
        chessPieceList.add(new Knight(this, new Coordinates(2, 8), Color.BLACK));
        chessPieceList.add(new Knight(this, new Coordinates(7, 8), Color.BLACK));
        //Add a king
        chessPieceList.add(new King(this, new Coordinates(5, 8), Color.BLACK));
        //Add a queen
        chessPieceList.add(new Queen(this, new Coordinates(4, 8), Color.BLACK));
    }

    public List<Move> availableMoves(Color color) {
        List<Move> potentials = new ArrayList<Move>();
        List<Move> availables = new ArrayList<Move>();
        for (ChessPiece chessPiece : chessPieceList) {
            if (chessPiece.getColor().equals(color)) {
                for (Coordinates coord : chessPiece.availableMovesWithoutAttack()) {
                    potentials.add(new Move(chessPiece, coord, false));
                }
                for (Coordinates coord : chessPiece.availableAttacks()) {
                    potentials.add(new Move(chessPiece, coord, true));
                }
            }
        }
        // FILTER (REMOVES) MOVES THAT MAKE THE KING CHECK
        for (Move move : potentials) {
            String boardSnapshot = this.toString();
            move.run();
            String boardAfterMove = this.toString();
            boolean isInCheck = isMyKingInCheck(move.getPiece().getColor());
            move.rollback();
            String boardSnapshotAfter = this.toString();
            if (!boardSnapshot.equals(boardSnapshotAfter)) {
                System.out.println("Rollback problem !");
                System.out.println(boardSnapshot);
                System.out.println(boardAfterMove);
                System.out.println(boardSnapshotAfter);
            }
            if (!isInCheck) {
                availables.add(move);
            }
        }
        return availables;
    }

    public boolean isMyKingInCheck(Color color) {
        boolean kingIsInCheck = false;
        //Locate king
        ChessPiece myKing = null;
        for (ChessPiece chessPiece : chessPieceList) {
            if (chessPiece.getClass().equals(King.class) && chessPiece.getColor().equals(color)) {
                myKing = chessPiece;
                break;
            }
        }
        //Check if one piece is attacking the king
        for (ChessPiece chessPiece : chessPieceList) {
            if (chessPiece.canAttack(myKing.getCoordinates())) {
                kingIsInCheck = true;
                break;
            }
        }
        return kingIsInCheck;
    }

    public ChessPiece locate(Coordinates coords) {
        for (ChessPiece chessPiece : chessPieceList) {
            if (chessPiece.getCoordinates().equals(coords)) {
                return chessPiece;
            }
        }
        return null;
    }

    public void removePiece(ChessPiece chessPieceToRemove) {
        ChessPiece foundPiece = null;
        for (ChessPiece chessPiece : chessPieceList) {
            if (chessPiece.getCoordinates().equals(chessPieceToRemove.getCoordinates())) {
                foundPiece = chessPiece;
                break;
            }
        }
        if (foundPiece != null) {
            chessPieceList.remove(foundPiece);
        }
    }

    public void addPiece(ChessPiece chessPieceToAdd) {
        chessPieceList.add(chessPieceToAdd);
    }

    public boolean coordIsValidAndEmpty(Coordinates coords) {
        return isCoordValid(coords) && locate(coords) == null;
    }

    public boolean coordContainsOpponent(Coordinates coords, Color myColor) {
        ChessPiece foundPiece = locate(coords);
        return foundPiece != null && !foundPiece.getColor().equals(myColor);
    }

    public boolean coordContainsOpponent(Coordinates coords, Color myColor, Class className) {
        ChessPiece foundPiece = locate(coords);
        return foundPiece != null && !foundPiece.getColor().equals(myColor) && foundPiece.getClass().equals(className);
    }

    private boolean isCoordValid(Coordinates coord) {
        return coord.getX() >= 1 && coord.getX() <= MAX_X && coord.getY() >= 1 && coord.getY() <= MAX_Y;
    }

    public ChessGame getGame() {
        return game;
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("");
        Collections.sort(chessPieceList, new Comparator<ChessPiece>() {
            @Override
            public int compare(ChessPiece o1, ChessPiece o2) {
                if (o1 == null) {
                    if (o2 == null) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    if (o2 == null) {
                        return 1;
                    } else {
                        return o1.toString().compareTo(o2.toString());
                    }
                }
            }
        });
        for (ChessPiece piece : chessPieceList) {
            if (piece != null) {
                boardStr.append(piece.toString() + " ");
            }
        }
        return boardStr.toString();
    }
}
