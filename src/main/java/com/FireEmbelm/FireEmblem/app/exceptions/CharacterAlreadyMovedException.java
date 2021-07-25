package com.FireEmbelm.FireEmblem.app.exceptions;

public class CharacterAlreadyMovedException extends Exception {
    public CharacterAlreadyMovedException() {
        super("Selected Character already moved in this turn");
    }
}
