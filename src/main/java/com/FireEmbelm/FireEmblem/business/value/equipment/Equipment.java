package com.FireEmbelm.FireEmblem.business.value.equipment;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;

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
