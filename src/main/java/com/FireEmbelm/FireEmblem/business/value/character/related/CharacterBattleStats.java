package com.FireEmbelm.FireEmblem.business.value.character.related;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;

import java.util.Objects;

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

        if(!(baseCharacter.getCurrentEquipedItem() == null)) {

            Equipment currentEquippedItem = baseCharacter.getCurrentEquipedItem();

            if( !(currentEquippedItem.getItemCategory() instanceof ConsumableItemCategory)
            && !(currentEquippedItem.getItemCategory().equals(WeaponCategory.STAFF))) {
                calculateAttack(baseCharacter, currentEquippedItem);
                mHitRate = ((Weapon) currentEquippedItem).getHit()
                        + ( ( ( baseCharacter.getStats().get(StatsType.SKILL).getValue()
                        + baseCharacter.getCharacterClass().getBonusStats().get(StatsType.SKILL).getValue() ) * 3
                        + baseCharacter.getStats().get(StatsType.LUCK).getValue()) / 2 );
                mCritical = ((Weapon) currentEquippedItem).getCrit()
                        + ( ( baseCharacter.getStats().get(StatsType.SKILL).getValue()
                        +  baseCharacter.getCharacterClass().getBonusStats().get(StatsType.SKILL).getValue() )/ 2 );
            }

        }

        mAvoid = ( ( baseCharacter.getStats().get(StatsType.SPEED).getValue()
                +  baseCharacter.getCharacterClass().getBonusStats().get(StatsType.SPEED).getValue() ) * 3
                + baseCharacter.getStats().get(StatsType.LUCK).getValue() ) / 2;
    }

    public void calculateAttack(BaseCharacter baseCharacter, Equipment currentEquippedItem) {

        if(currentEquippedItem.getItemCategory().equals(WeaponCategory.TOME)) {
            mAttack = baseCharacter.getStats().get(StatsType.MAGICK).getValue() + currentEquippedItem.getMight()
                    + baseCharacter.getCharacterClass().getBonusStats().get(StatsType.MAGICK).getValue();
        }
        else {
            mAttack = baseCharacter.getStats().get(StatsType.STRENGTH).getValue() + currentEquippedItem.getMight()
                    + baseCharacter.getCharacterClass().getBonusStats().get(StatsType.STRENGTH).getValue();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterBattleStats that = (CharacterBattleStats) o;
        return getAttack() == that.getAttack()
                && getHitRate() == that.getHitRate()
                && getCritical() == that.getCritical()
                && getAvoid() == that.getAvoid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttack(), getHitRate(), getCritical(), getAvoid());
    }
}
