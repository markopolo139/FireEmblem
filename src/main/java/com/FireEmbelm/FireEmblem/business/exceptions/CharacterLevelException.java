package com.FireEmbelm.FireEmblem.business.exceptions;

public class CharacterLevelException extends Exception {
    public CharacterLevelException() {
        super("Character level is too low");
    }
}
