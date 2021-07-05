package com.FireEmbelm.FireEmblem.business.exceptions;

public class NoWeaponException extends Exception {
    public NoWeaponException() {
        super("No weapon currently equiped");
    }

    public NoWeaponException(String message) {
        super(message);
    }
}
