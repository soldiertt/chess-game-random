package be.smals.model;

/**
 * Created by soldiertt on 05-06-14.
 */
public enum Color {
    WHITE ("WHITE"),
    BLACK ("BLACK");

    private String name = "";

    //Constructeur
    Color(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
