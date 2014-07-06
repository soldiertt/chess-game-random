package be.smals.util;

import be.smals.model.Color;
import be.smals.model.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soldiertt on 05-06-14.
 */
public class CoordUtil {

    public static Coordinates forward(Coordinates from, int value, Color color) {
        Coordinates newCoord = new Coordinates(from.getX(), from.getY());
        if (color.equals(Color.WHITE)) {
            newCoord.setY(newCoord.getY() + value);
        } else {
            newCoord.setY(newCoord.getY() - value);
        }
        return newCoord;
    }

    public static Coordinates backward(Coordinates from, int value, Color color) {
        Coordinates newCoord = new Coordinates(from.getX(), from.getY());
        if (color.equals(Color.WHITE)) {
            newCoord.setY(newCoord.getY() - value);
        } else {
            newCoord.setY(newCoord.getY() + value);
        }
        return newCoord;
    }

    public static Coordinates left(Coordinates from, int value, Color color) {
        Coordinates newCoord = new Coordinates(from.getX(), from.getY());
        if (color.equals(Color.WHITE)) {
            newCoord.setX(newCoord.getX() - value);
        } else {
            newCoord.setX(newCoord.getX() + value);
        }
        return newCoord;
    }

    public static Coordinates right(Coordinates from, int value, Color color) {
        Coordinates newCoord = new Coordinates(from.getX(), from.getY());
        if (color.equals(Color.WHITE)) {
            newCoord.setX(newCoord.getX() + value);
        } else {
            newCoord.setX(newCoord.getX() - value);
        }
        return newCoord;
    }

    public static Coordinates forwardObliqueLeft(Coordinates from, int value, Color color) {
        Coordinates newCoord = new Coordinates(from.getX(), from.getY());
        if (color.equals(Color.WHITE)) {
            newCoord.setY(newCoord.getY() + value);
            newCoord.setX(newCoord.getX() - value);
        } else {
            newCoord.setY(newCoord.getY() - value);
            newCoord.setX(newCoord.getX() + value);
        }
        return newCoord;
    }

    public static Coordinates forwardObliqueRight(Coordinates from, int value, Color color) {
        Coordinates newCoord = new Coordinates(from.getX(), from.getY());
        if (color.equals(Color.WHITE)) {
            newCoord.setY(newCoord.getY() + value);
            newCoord.setX(newCoord.getX() + value);
        } else {
            newCoord.setY(newCoord.getY() - value);
            newCoord.setX(newCoord.getX() - value);
        }
        return newCoord;
    }

    public static Coordinates backwardObliqueLeft(Coordinates from, int value, Color color) {
        Coordinates newCoord = new Coordinates(from.getX(), from.getY());
        if (color.equals(Color.WHITE)) {
            newCoord.setY(newCoord.getY() - value);
            newCoord.setX(newCoord.getX() - value);
        } else {
            newCoord.setY(newCoord.getY() + value);
            newCoord.setX(newCoord.getX() + value);
        }
        return newCoord;
    }

    public static Coordinates backwardObliqueRight(Coordinates from, int value, Color color) {
        Coordinates newCoord = new Coordinates(from.getX(), from.getY());
        if (color.equals(Color.WHITE)) {
            newCoord.setY(newCoord.getY() - value);
            newCoord.setX(newCoord.getX() + value);
        } else {
            newCoord.setY(newCoord.getY() + value);
            newCoord.setX(newCoord.getX() - value);
        }
        return newCoord;
    }

    public static List<Coordinates> knightMoves(Coordinates from) {
        List<Coordinates> moves = new ArrayList<Coordinates>();
        moves.add(new Coordinates(from.getX() - 2, from.getY() - 1));
        moves.add(new Coordinates(from.getX() - 2, from.getY() + 1));
        moves.add(new Coordinates(from.getX() - 1, from.getY() + 2));
        moves.add(new Coordinates(from.getX() - 1, from.getY() - 2));
        moves.add(new Coordinates(from.getX() + 1, from.getY() - 2));
        moves.add(new Coordinates(from.getX() + 1, from.getY() + 2));
        moves.add(new Coordinates(from.getX() + 2, from.getY() + 1));
        moves.add(new Coordinates(from.getX() + 2, from.getY() - 1));
        return moves;
    }
}
