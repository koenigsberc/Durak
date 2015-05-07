package de.htwg.model;

import java.util.LinkedList;

/**
 * Created by jawaigel on 06.05.2015.
 */
public class Field {

    private LinkedList<PlayingCard> field;

    public Field(){
        field = new LinkedList<>();
    }

    public void addCard(PlayingCard card){
        field.add(card);
    }

    public PlayingCard getCard(){
        return field.remove(0);
    }

    public LinkedList<PlayingCard> getField(){
        return field;
    }

    public void clearField(){
        field = new LinkedList<>();
    }

    public int getFieldSize() {
        return field.size();
    }

    public String toString(){

        String s = "";

        for (PlayingCard p : field){
            s += p.toString();
        }
        return s;
    }
}
