package com.FireEmbelm.FireEmblem.business.value.character.related;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;

public class CharacterBattleStats {

    private int mAttack;
    private int mHitRate;
    private int mCritical;
    private int mAvoid;

    public int getAttack() {
        return mAttack;
    }

    public void setAttack(int attack) {
        mAttack = attack;
    }

    public int getHitRate() {
        return mHitRate;
    }

    public void setHitRate(int hitRate) {
        mHitRate = hitRate;
    }

    public int getCritical() {
        return mCritical;
    }

    public void setCritical(int critical) {
        mCritical = critical;
    }

    public int getAvoid() {
        return mAvoid;
    }

    public void setAvoid(int avoid) {
        mAvoid = avoid;
    }

    public CharacterBattleStats(BaseCharacter baseCharacter) {
        calculateBattleStats(baseCharacter);
    }

    public void calculateBattleStats(BaseCharacter baseCharacter) {

    }

    public void calculateAttack(BaseCharacter baseCharacter) {
        Equipment currentEquipedItem = baseCharacter.getCurrentEquipedItem();

        if(currentEquipedItem.getItemCategory().equals(ItemCategory.TOME)) {
            mAttack = baseCharacter.getStats().get(Stats.MAGICK.name()).getValue() + currentEquipedItem.getMight();
        }
        else {
            mAttack = baseCharacter.getStats().get(Stats.STRENGTH.name()).getValue() + currentEquipedItem.getMight();
        }
    }
}
