package com.oc.cardgame.games;

import com.oc.cardgame.model.IPlayer;

import java.util.List;

public interface GameEvaluator {
     public IPlayer evaluateWinner(List<IPlayer> players);
}
