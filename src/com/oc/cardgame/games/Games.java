package com.oc.cardgame.games;

import com.oc.cardgame.controller.GameController;
import com.oc.cardgame.model.Deck;
import com.oc.cardgame.model.DeckFactory;
import com.oc.cardgame.view.CommandLineView;
import com.oc.cardgame.view.GameSwingView;

public class Games {
    public static void main(String[] args) {

        GameSwingView gsw = new GameSwingView();
        gsw.createAndShowGUI();
        GameController gc = new GameController(DeckFactory.makeDeck(DeckFactory.DeckType.Normal), gsw, new HighCardGameEvaluator());
        gc.run();
    }
}
