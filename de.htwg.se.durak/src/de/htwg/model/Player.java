package de.htwg.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jawaigel on 16.04.2015.
 */
public abstract class Player implements IPlayer {

    private List<PlayingCard> playersHand;

    public abstract PlayingCard attack(List<PlayingCard> cardsOnField, int cardIndex);

    public abstract PlayingCard defend(PlayingCard cardToBeat, int cardIndex);

    public Player(){
        playersHand = new LinkedList<PlayingCard>();
    }

    public void drawCard(PlayingCard card){
        if(this instanceof HumanPlayer) {
            card.setHide(false);
        }
        else {
            card.setHide(true);
        }

        playersHand.add(card);
        this.sortHand();
    }


    @Override
    public List<PlayingCard> getPlayersHand() {
        return playersHand;
    }

    public void setTrumpOnHand(PlayingCardColor trump){
        for (PlayingCard cardOnHand : playersHand) {
            if (cardOnHand.getColor() == trump) {
                cardOnHand.setTrump();
            }

        }
    }

    @Override
    public void sortHand() {
        Collections.sort(playersHand, new CardComparator());
    }

    @Override
    public String toString(){
        String str = "\t\t";
        for(PlayingCard card: playersHand){
            str += card.toString() + "\t";
        }
        return str;
    }

    private class CardComparator implements Comparator<PlayingCard> {
        @Override
        public int compare(PlayingCard card1, PlayingCard card2)
        {
            return card1.getValue().ordinal() - card2.getValue().ordinal();
        }
    }
}
