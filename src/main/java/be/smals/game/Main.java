package be.smals.game;

import be.smals.exception.ChessGameException;
import be.smals.model.ChessGame;
import be.smals.model.Color;
import be.smals.model.Move;

import java.util.List;

/**
 * Created by soldiertt on 05-06-14.
 */
public class Main {

    public static void main(String[] args) {

        ChessGame game = new ChessGame();

        String endingMessage;
        Color color = Color.WHITE;

        System.out.println("START NEW GAME");

        do {
            try {
                game.move(color);
                color = color.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
            } catch (ChessGameException e) {
                endingMessage = e.getMessage();
                break;
            }
        } while (true);



        List<Move> allMoves = game.getMovesHistory();
        int index = 1;
        for (Move move : allMoves) {
            if (move.getPiece().getColor().equals(Color.WHITE)) {
                System.out.print(index++ + ". " + move);
            } else {
                System.out.println(" " + move);
            }
        }
        System.out.println("");
        System.out.println("GAME OVER");
        System.out.println(endingMessage);

    }
}
