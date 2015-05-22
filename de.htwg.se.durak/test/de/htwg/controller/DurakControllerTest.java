package de.htwg.controller;

import de.htwg.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DurakControllerTest {

    DurakController controller;
    Player p1, p2;
    Deck deck;

    @Before
    public void setUp() throws Exception {
        controller = new DurakController();
        p1 = new HumanPlayer();
        p2 = new ComputerPlayer();
        deck = new Deck();

        deck.addCard(new PlayingCard(PlayingCardValue.SIX, PlayingCardColor.HEARTS));
    }

    @After
    public void tearDown() throws Exception {
        controller = null;
        p1 = null;
        p2 = null;
        deck = null;
    }

    @Test
    public void testPlayerMove() throws Exception {

    }

    @Test
    public void testGetPlayersHand() throws Exception {

    }

    @Test
    public void testGetComputerHand() throws Exception {

    }

    @Test
    public void testGetField() throws Exception {

    }

    @Test
    public void testIsHumanPlayer() throws Exception {

    }

    @Test
    public void testGetTrump() throws Exception {
    }

    @Test
    public void testGetDeckSize() throws Exception {
        int size = controller.getDeckSize();
        assertEquals(1, size);
    }

    @Test
    public void testIsInvalidPlayerInput() throws Exception {

    }
}