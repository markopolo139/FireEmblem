package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.NoWeaponException;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterBattleStats;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

import java.util.Random;

//TODO : test
// front-end inform user if using weapon with last use
// in interactor : check status (if dead, set spot to null and save dead character, and spot with null) (if alive, save spot with character
public class BattleService {

    private final Random mRandom = new Random();
    private final CharacterDevelopmentService mCharacterDevelopmentService;

    public CharacterDevelopmentService getCharacterDevelopmentService() {
        return mCharacterDevelopmentService;
    }

    public void initialiseBattle(Spot attackerSpot, Spot defenderSpot, ItemsConvoy itemsConvoy)
            throws NoWeaponException, OutOfRangeException {

        BaseCharacter attacker = attackerSpot.getCharacterOnSpot();
        BaseCharacter defender = defenderSpot.getCharacterOnSpot();

        if( !(attacker.getCurrentEquipedItem().getItemCategory() instanceof WeaponCategory) )
            throw new NoWeaponException("Can't attack without a weapon");

        isDefenderInWeaponRange(attackerSpot,defenderSpot);
        calculateDamageAndSetRemainingHp(attackerSpot,defenderSpot);

        if(defender.getCharacterState().equals(CharacterState.ALIVE)) {

            mCharacterDevelopmentService.increaseExpNotDead(attacker,defender);

            if (defender.getCurrentEquipedItem().getRange() == attacker.getCurrentEquipedItem().getRange()
                && defender.getCurrentEquipedItem().getItemCategory() instanceof WeaponCategory) {

                calculateDamageAndSetRemainingHp(defenderSpot, attackerSpot);
                gettingExpAndMoney(defender, attacker, itemsConvoy);
                mCharacterDevelopmentService.increaseWeaponProgress(defender);

                if(defender.getCharacterState().equals(CharacterState.DEAD))
                    addWeaponAndMoneyFromEnemyToConvoy(defender, itemsConvoy);

            }
            else {
                if(isDoubleAttack(attacker,defender)) {

                    calculateDamageAndSetRemainingHp(attackerSpot, defenderSpot);
                    gettingExpAndMoney(attacker, defender, itemsConvoy);

                }
            }
        }
        else {
            mCharacterDevelopmentService.increaseExpDead(attacker,defender);
            addWeaponAndMoneyFromEnemyToConvoy(defender, itemsConvoy);
        }

        mCharacterDevelopmentService.increaseWeaponProgress(attacker);
        updateWeaponUse(attacker);
        updateWeaponUse(defender);

        attacker.setMoved(true);

    }

    public void isDefenderInWeaponRange(Spot attackerSpot, Spot defenderSpot) throws OutOfRangeException {
        int distanceBetweenCharacter = Math.abs(attackerSpot.getHeight() - defenderSpot.getHeight())
                + Math.abs(attackerSpot.getWidth() - defenderSpot.getWidth());

        if(attackerSpot.getCharacterOnSpot().getCurrentEquipedItem().getRange() != distanceBetweenCharacter)
            throw new OutOfRangeException("Attacked character is out of range, with current equiped weapon");
    }

    public void calculateDamageAndSetRemainingHp(Spot attackingCharacterSpot, Spot defendingCharacterSpot) {

        if(didAttackHit(attackingCharacterSpot, defendingCharacterSpot)) {

            int howMuchDamageDealt = calculateDamageDealt(attackingCharacterSpot, defendingCharacterSpot);

            if(isAttackCrit(
                    attackingCharacterSpot.getCharacterOnSpot().getCharacterBattleStats(),
                    defendingCharacterSpot.getCharacterOnSpot())
            )
                howMuchDamageDealt *= 3;

            defendingCharacterSpot.getCharacterOnSpot().setRemainingHealth(
                    defendingCharacterSpot.getCharacterOnSpot().getRemainingHealth() - howMuchDamageDealt
            );

            if (defendingCharacterSpot.getCharacterOnSpot().getRemainingHealth() <= 0) {
                defendingCharacterSpot.getCharacterOnSpot().setCharacterState(CharacterState.DEAD);
            }

        }
    }

    public int calculateDamageDealt(Spot attackingCharacterSpot, Spot defendingCharacterSpot) {
        if(! attackingCharacterSpot.getCharacterOnSpot().getCurrentEquipedItem().getItemCategory().equals(WeaponCategory.TOME)) {
            return  attackingCharacterSpot.getCharacterOnSpot().getCharacterBattleStats().getAttack()
                    - ( defendingCharacterSpot.getCharacterOnSpot().getStats().get(StatsType.DEFENSE).getValue()
                    + defendingCharacterSpot.getSpotsType().getDefBoost()
                    + defendingCharacterSpot.getCharacterOnSpot().getCharacterClass().getBonusStats().get(StatsType.DEFENSE).getValue());
        }
        else {
            return  attackingCharacterSpot.getCharacterOnSpot().getCharacterBattleStats().getAttack()
                    - ( defendingCharacterSpot.getCharacterOnSpot().getStats().get(StatsType.RESISTANCE).getValue()
                    + defendingCharacterSpot.getCharacterOnSpot().getCharacterClass().getBonusStats().get(StatsType.RESISTANCE).getValue());
        }
    }

    public boolean didAttackHit(Spot attackingSpot, Spot defendingSpot) {
        return ( attackingSpot.getCharacterOnSpot().getCharacterBattleStats().getHitRate()
                - defendingSpot.getCharacterOnSpot().getCharacterBattleStats().getAvoid()
                - defendingSpot.getSpotsType().getAvoBoost() ) >= mRandom.nextInt(101);
    }

    public boolean isAttackCrit(CharacterBattleStats attackingStats, BaseCharacter defendingCharacter) {
        return (attackingStats.getCritical() - defendingCharacter.getStats().get(StatsType.LUCK).getValue())
                >= mRandom.nextInt(101);
    }

    public boolean isDoubleAttack(BaseCharacter attacker, BaseCharacter defender) {
        return ( (attacker.getStats().get(StatsType.SPEED).getValue()
                + attacker.getCharacterClass().getBonusStats().get(StatsType.SPEED).getValue())
                - (defender.getStats().get(StatsType.SPEED).getValue()
                + defender.getCharacterClass().getBonusStats().get(StatsType.SPEED).getValue() )) >= 5;
    }

    public void gettingExpAndMoney(BaseCharacter getExperience, BaseCharacter checkIfDead, ItemsConvoy itemsConvoy) {

        if(checkIfDead.getCharacterState().equals(CharacterState.DEAD)) {
            mCharacterDevelopmentService.increaseExpDead(getExperience,checkIfDead);
            addWeaponAndMoneyFromEnemyToConvoy(checkIfDead, itemsConvoy);

        }
        else {
            mCharacterDevelopmentService.increaseExpNotDead(getExperience,checkIfDead);
        }
    }

    public void addWeaponAndMoneyFromEnemyToConvoy(BaseCharacter deadCharacter, ItemsConvoy itemsConvoy) {
        if(deadCharacter instanceof Enemy) {

            itemsConvoy.setMoney(itemsConvoy.getMoney() + ((Enemy) deadCharacter).getGoldDrop());

            if(((Enemy) deadCharacter).getDropItem() != null)
                itemsConvoy.getEquipmentCollection().add(((Enemy) deadCharacter).getDropItem());

        }
    }

    public void updateWeaponUse(BaseCharacter baseCharacter) {

        if(baseCharacter.getCharacterState().equals(CharacterState.DEAD)) {
            return;
        }

        baseCharacter.getCurrentEquipedItem().setUses(baseCharacter.getCurrentEquipedItem().getUses() - 1);
        if (baseCharacter.getCurrentEquipedItem().getUses() == 0) {
            baseCharacter.setCurrentEquipedItem(null);
        }
    }

    public BattleService(CharacterDevelopmentService characterDevelopmentService) {
        mCharacterDevelopmentService = characterDevelopmentService;
    }
}
