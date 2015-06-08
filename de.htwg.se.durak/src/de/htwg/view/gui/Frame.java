package de.htwg.view.gui;

import de.htwg.controller.DurakController;
import de.htwg.model.HumanPlayer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by jawaigel on 07.05.2015.
 */
public class Frame extends JFrame implements ActionListener, Observer{

    private static final int DEFAULT_X = 800;
    private static final int DEFAULT_Y = 420;
    private static final Dimension CARD_SIZE_DIMENSION = new Dimension(50, 80);
    private static final Dimension STATUS_BAR_DIMENSION = new Dimension(DEFAULT_X, 20);
    private static final GridLayout CARD_PANEL_LAYOUT = new GridLayout(3, 1, 5, 5);
    private static final GridLayout DECK_PANEL_LAYOUT = new GridLayout(2, 1);
    private static final GridLayout ACTION_PANEL_LAYOUT = new GridLayout(2, 1);
    private static final BorderLayout PANE_LAYOUT = new BorderLayout(5, 5);

    private static final String CMD_TAKE = "t";
    private static final String CMD_SKIP = "0";
    private static final String MSG_COMPUTER_WIN = "Computer hat gewonnen!";
    private static final String MSG_PLAYER_WIN = "Spieler hat gewonnen!";
    private static final String MSG_PLAYER_MOVE = "Spieler am Zug";
    private static final String MSG_INVALID_MOVE = "Spielzug nicht möglich!";


    private FieldCardPanel panelField;
    private PlayerCardPanel panelComputerPlayer, panelHumanPlayer;
    private JButton playerTakeBtn, playerSkipBtn, trumpBtn, deckBtn;
    private JLabel statusMsg;

    private DurakController controller;

    public Frame(DurakController controller) {

        this.controller = controller;

        this.setTitle("дурак");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_X, DEFAULT_Y);

        //Add Card Panels
        panelComputerPlayer = new PlayerCardPanel(controller, controller.getComputerHand());
        panelHumanPlayer = new PlayerCardPanel(controller, controller.getPlayersHand());
        panelField = new FieldCardPanel(controller, controller.getField());

        this.controller.addObserver(this);

        playerTakeBtn = new JButton("take");
        playerTakeBtn.addActionListener(this);

        playerSkipBtn = new JButton("end turn");
        playerSkipBtn.addActionListener(this);

        trumpBtn = new JButton(controller.getTrump().toString());
        trumpBtn.setPreferredSize(CARD_SIZE_DIMENSION);
        deckBtn = new JButton(Integer.toString(controller.getDeckSize()));
        deckBtn.setPreferredSize(CARD_SIZE_DIMENSION);

        statusMsg = new JLabel();
        statusMsg.setHorizontalAlignment(SwingConstants.CENTER);
        statusMsg.setVerticalAlignment(SwingConstants.CENTER);

        JPanel cardPanel = new JPanel(CARD_PANEL_LAYOUT);
        cardPanel.add(panelComputerPlayer);
        cardPanel.add(panelField);
        cardPanel.add(panelHumanPlayer);

        JPanel deckPanel = new JPanel(DECK_PANEL_LAYOUT);
        deckPanel.add(trumpBtn);
        deckPanel.add(deckBtn);

        Container pane = this.getContentPane();
        pane.setLayout(PANE_LAYOUT);
        pane.add(deckPanel, BorderLayout.LINE_START);
        pane.add(cardPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(ACTION_PANEL_LAYOUT);
        actionPanel.add(playerTakeBtn);
        actionPanel.add(playerSkipBtn);
        pane.add(actionPanel, BorderLayout.EAST);

        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(new LineBorder(Color.GRAY));
        statusBar.setPreferredSize(STATUS_BAR_DIMENSION);
        statusBar.add(statusMsg, BorderLayout.CENTER);
        pane.add(statusBar, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(playerTakeBtn)) {
            controller.playerMove(CMD_TAKE);
        } else if (e.getSource().equals(playerSkipBtn)) {
            controller.playerMove(CMD_SKIP);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if(controller.getWinPlayer() != null) {
            playerSkipBtn.setEnabled(false);
            playerTakeBtn.setEnabled(false);

            panelField.disableField();
            panelHumanPlayer.disableField();
            panelComputerPlayer.disableField();

            if(controller.getWinPlayer() instanceof HumanPlayer) {
                statusMsg.setText(MSG_PLAYER_WIN);
            } else {
                statusMsg.setText(MSG_COMPUTER_WIN);
            }
            return;
       }

        if(controller.isInvalidPlayerInput()) {
            statusMsg.setText(MSG_INVALID_MOVE);
        } else if (controller.isHumanPlayer()){
            statusMsg.setText(MSG_PLAYER_MOVE);
        }

        deckBtn.setText(Integer.toString(controller.getDeckSize()));
    }

}
