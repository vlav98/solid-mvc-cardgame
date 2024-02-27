package com.oc.cardgame;

import com.oc.cardgame.controller.GameController;
import com.oc.cardgame.games.HighCardGameEvaluator;
import com.oc.cardgame.view.GameSwigPassiveView;
import com.oc.cardgame.view.GameSwingView;
import com.oc.cardgame.view.GameViewables;

public class Games {
    public static void main(String[] args) {
        GameViewables views = new GameViewables();

        GameSwingView gsw = new GameSwingView();
        gsw.createAndShowGUI();

        views.addViewable(gsw);

        for (int i = 0; i<3; i++) {
            GameSwigPassiveView passiveView = new GameSwigPassiveView();
            passiveView.createAndShowGUI();

            views.addViewable(passiveView);

            // sleep to move new Swing frame on window
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        GameController gc = new GameController(DeckFactory.makeDeck(DeckFactory.DeckType.Normal), views, new HighCardGameEvaluator());
        gc.run();
    }
}
