package com.oc.cardgame.model;

public enum Suit {
    DIAMOND (1),
    HEARTS (1),
    SPADES (3),
    CLUBS (4);

    int suit;

    private Suit(int value) {
        suit = value;
    }

    public int value() {
        return suit;
    }
}
