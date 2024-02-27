package com.oc.cardgame.controller;

import com.oc.cardgame.games.GameEvaluator;
import com.oc.cardgame.model.*;
import com.oc.cardgame.view.GameViewable;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    enum GameState {
        AddingPlayers, CardsDealt, WinnerRevealed;
    }

    Deck deck;
    List<IPlayer> players;
    IPlayer winner;
    GameViewable view;
    GameState gameState;
    GameEvaluator evaluator;

    public GameController(Deck deck, GameViewable view, GameEvaluator evaluator) {
        super();
        this.deck = deck;
        this.view = view;
        this.players = new ArrayList<IPlayer>();
        this.gameState = GameState.AddingPlayers;
        view.setController(this);
        this.evaluator = evaluator;
    }

    public void run() {
        while (gameState == GameState.AddingPlayers) {
            view.promptForPlayerName();
        }

        switch (gameState) {
            case CardsDealt -> view.promptForFlip();
            case WinnerRevealed -> view.promptForNewGame();
        }
    }

    public void addPlayer(String playerName) {
        if (gameState == GameState.AddingPlayers) {
            players.add(new Player(playerName));
            view.showPlayerName(players.size(), playerName);
        }
    }

    public void startGame() {
        if(gameState != GameState.CardsDealt) {
            deck.shuffle();
            int playerIndex = -1;
            for (IPlayer player: players) {
                player.addCardToHand(deck.removeTopCard());
                view.showFaceDownCardForPlayer(playerIndex++, player.getName());
            }
            gameState = GameState.CardsDealt;
        }
        this.run();
    }

    public void flipCards() {
        int playerIndex = -1;
        for (IPlayer player: players) {
            PlayingCard pc = player.getCard(0);
            pc.flip();
            view.showCardForPlayer(playerIndex++, player.getName(), pc.getRank().toString(), pc.getSuit().toString());
        }

        evaluateWinner();
        displayWinner();
        rebuildDeck();
        gameState = GameState.WinnerRevealed;
        this.run();
    }

    void evaluateWinner() {
        winner = new WinningPlayer(evaluator.evaluateWinner(players));
    }

    void displayWinner() {
        view.showWinner(winner.getName());
    }

    void rebuildDeck() {
        for (IPlayer player: players) {
            deck.returnCardToDeck(player.removeCard());
        }
    }

    void exitGame() {
        System.exit(0);
    }

    public void nextAction(String nextChoice) {
        if("+q".equals(nextChoice)) {
            exitGame();
        } else {
            startGame();
        }
    }
}
