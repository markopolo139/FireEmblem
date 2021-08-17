package com.FireEmbelm.FireEmblem.app.exceptions;

import javassist.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}
