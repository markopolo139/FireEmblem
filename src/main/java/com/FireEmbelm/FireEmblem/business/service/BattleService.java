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
// include spots bonus in calculation
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
        calculateDamageAndSetRemainingHp(attacker,defender);

        if(defender.getCharacterState().equals(CharacterState.ALIVE)) {

            mCharacterDevelopmentService.increaseExpNotDead(attacker,defender);

            if (defender.getCurrentEquipedItem().getRange() == attacker.getCurrentEquipedItem().getRange()
                && defender.getCurrentEquipedItem().getItemCategory() instanceof WeaponCategory) {

                calculateDamageAndSetRemainingHp(defender,attacker);
                gettingExpAndMoney(defender, attacker, itemsConvoy);
                mCharacterDevelopmentService.increaseWeaponProgress(defender);

                if(defender.getCharacterState().equals(CharacterState.DEAD))
                    addWeaponAndMoneyFromEnemyToConvoy(defender, itemsConvoy);

            }
            else {
                if(isDoubleAttack(attacker,defender)) {

                    calculateDamageAndSetRemainingHp(attacker,defender);
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

    }

    public void isDefenderInWeaponRange(Spot attackerSpot, Spot defenderSpot) throws OutOfRangeException {
        int distanceBetweenCharacter = Math.abs(attackerSpot.getHeight() - defenderSpot.getHeight())
                + Math.abs(attackerSpot.getWidth() - defenderSpot.getWidth());

        if(attackerSpot.getCharacterOnSpot().getCurrentEquipedItem().getRange() != distanceBetweenCharacter)
            throw new OutOfRangeException("Attacked character is out of range, with current equiped weapon");
    }

    public void calculateDamageAndSetRemainingHp(BaseCharacter attackingCharacter, BaseCharacter defendingCharacter) {
        if(didAttackHit(attackingCharacter.getCharacterBattleStats(),
                        defendingCharacter.getCharacterBattleStats())) {

            int howMuchDamageDealt = attackingCharacter.getCharacterBattleStats().getAttack()
                    - defendingCharacter.getStats().get(StatsType.DEFENSE).getValue();

            if(isAttackCrit(attackingCharacter.getCharacterBattleStats(), defendingCharacter))
                howMuchDamageDealt *= 3;

            defendingCharacter.setRemainingHealth(defendingCharacter.getRemainingHealth() - howMuchDamageDealt);

            if (defendingCharacter.getRemainingHealth() <= 0) {
                defendingCharacter.setCharacterState(CharacterState.DEAD);
            }

        }
    }

    public boolean didAttackHit(CharacterBattleStats attackingStats, CharacterBattleStats defendingStats) {
        return (attackingStats.getHitRate() - defendingStats.getAvoid()) >= mRandom.nextInt(101);
    }

    public boolean isAttackCrit(CharacterBattleStats attackingStats, BaseCharacter defendingCharacter) {
        return (attackingStats.getCritical() - defendingCharacter.getStats().get(StatsType.LUCK).getValue())
                >= mRandom.nextInt(101);
    }

    public boolean isDoubleAttack(BaseCharacter attacker, BaseCharacter defender) {
        return ( attacker.getStats().get(StatsType.SPEED).getValue()
                - defender.getStats().get(StatsType.SPEED).getValue() ) >= 5;
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
