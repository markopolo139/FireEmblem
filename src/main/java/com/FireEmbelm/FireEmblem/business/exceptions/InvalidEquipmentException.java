package com.FireEmbelm.FireEmblem.business.exceptions;

public class InvalidEquipmentException extends Exception {
    public InvalidEquipmentException() {
        super("Selected item is invalid");
    }

    public InvalidEquipmentException(String message) {
        super(message);
    }
}
