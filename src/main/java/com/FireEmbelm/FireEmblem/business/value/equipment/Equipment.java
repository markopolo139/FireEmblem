package com.FireEmbelm.FireEmblem.business.value.equipment;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.web.models.request.HealingItemModel;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponModel;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "equipmentType")
@JsonSubTypes({
        @Type(value = Weapon.class, name = "weapon"),
        @Type(value = WeaponModel.class, name = "weaponModel"),
        @Type(value = HealingItemWithUses.class, name = "healingItem"),
        @Type(value = HealingItemModel.class, name = "healingItemModel"),
        @Type(value = Seals.class, name = "seals"),
        @Type(value = StatsUpItems.class, name = "statsUpItems")
})
public interface Equipment {

    String getName();

    int getMight();

    int getWorth();

    int getRange();

    int getUses();

    void setUses(int uses);

    ItemCategory getItemCategory();

    //Description should be in frontEnd
    String getDescription();

}
