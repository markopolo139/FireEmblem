package com.FireEmbelm.FireEmblem.business.exceptions;

public class SealEquipedException extends Exception {
    public SealEquipedException() {
        super("Seal must be used to promote character");
    }
}
