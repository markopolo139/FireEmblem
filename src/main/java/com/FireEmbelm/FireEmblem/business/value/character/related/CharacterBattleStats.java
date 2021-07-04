package com.FireEmbelm.FireEmblem.business.value.character.related;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;

public class CharacterBattleStats {

    private int mAttack = 0;
    private int mHitRate = 0;
    private int mCritical = 0;
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

        Equipment currentEquipedItem = baseCharacter.getCurrentEquipedItem();

        if( !(currentEquipedItem.getItemCategory() instanceof ConsumableItemCategory) ) {
            calculateAttack(baseCharacter, currentEquipedItem);
            mHitRate = ((Weapon) currentEquipedItem).getHit()
                    + ( (baseCharacter.getStats().get(StatsType.SKILL).getValue() * 3
                    + baseCharacter.getStats().get(StatsType.LUCK).getValue()) / 2 );
            mCritical = ((Weapon) currentEquipedItem).getCrit()
                    + (baseCharacter.getStats().get(StatsType.SKILL).getValue() / 2);
        }

        mAvoid = (baseCharacter.getStats().get(StatsType.SPEED).getValue() * 3
                + baseCharacter.getStats().get(StatsType.LUCK).getValue()) / 2;
    }

    public void calculateAttack(BaseCharacter baseCharacter, Equipment currentEquipedItem) {

        if(currentEquipedItem.getItemCategory().equals(WeaponCategory.TOME)) {
            mAttack = baseCharacter.getStats().get(StatsType.MAGICK).getValue() + currentEquipedItem.getMight();
        }
        else {
            mAttack = baseCharacter.getStats().get(StatsType.STRENGTH).getValue() + currentEquipedItem.getMight();
        }
    }
}
