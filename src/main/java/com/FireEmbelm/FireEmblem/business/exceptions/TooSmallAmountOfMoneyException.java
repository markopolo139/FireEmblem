package com.FireEmbelm.FireEmblem.business.exceptions;

public class TooSmallAmountOfMoneyException extends Exception {
    public TooSmallAmountOfMoneyException() {
        super("You have too small amount of money too buy this equipment");
    }
}
