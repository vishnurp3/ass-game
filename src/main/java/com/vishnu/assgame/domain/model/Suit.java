package com.vishnu.assgame.domain.model;

public enum Suit {
    CLUB, DIAMOND, HEART, SPADE;

    public String displayName() {
        String lowercase = name().toLowerCase();
        return Character.toUpperCase(lowercase.charAt(0)) + lowercase.substring(1);
    }
}
