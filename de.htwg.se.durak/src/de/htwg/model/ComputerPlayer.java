package de.htwg.model;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by jan-erikwaigel on 16.04.15.
 */
public class ComputerPlayer extends Player {

    /**
     * The Names.
     */
    public String names[] = {"Elif", "Esac", "Julio", "Jamtonio", "LaDasha", "Schlomo", "Micha", "Walpurga", "Ralle",
            "Karma", "Vladi", "Mustafa", "Yildirim", "Lothar", "Roland", "CR7", "Mausi96", "Allah"};

    /**
     * Instantiates a new Computer player.
     */
    public ComputerPlayer(){
        playerName = "BOT " + names[new Random().nextInt(names.length - 1)];
    }

    /**
     * Play card.
     *
     * @return the playing card [ ]
     * @param cardsOnField
     */
    @Override
    public PlayingCard attack(LinkedList<PlayingCard> cardsOnField) {

        this.sortHand();
        if (cardsOnField.isEmpty()) return hand.get(0);

        return scanField(cardsOnField);
    }

    @Override
    public PlayingCard defend(PlayingCard cardToBeat) {
        PlayingCard cardDefend = null;

        for (PlayingCard cardOnHand : hand){
            if ((cardOnHand.getColor() == cardToBeat.getColor()
                    && (cardOnHand.getValue().ordinal() > cardToBeat.getValue().ordinal()))) {
                 return cardOnHand;
            }
            else if (cardOnHand.isTrump()) {
                cardDefend = cardOnHand;
            }
        }

        return cardDefend;
    }

    public PlayingCard scanField(LinkedList<PlayingCard> currentField) {

        for (PlayingCard cardOnField : currentField) {
            for (PlayingCard cardOnHand : hand) {
                if (cardOnHand.getValue() == cardOnField.getValue())
                    return cardOnHand;
            }
        }

        return null;
    }
}
