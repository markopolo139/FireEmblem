package com.FireEmbelm.FireEmblem.business.value;

//TODO : ask Janek
public interface Equipment {

    String getName();

    int getMight();

    int getWorth();

    int getRange();

    int getUses();

    ItemCategory getItemCategory();

    //Description should be in frontEnd
    String getDescription();

}
