package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.NoWeaponException;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;

//TODO : test
// front-end inform user if using weapon with last use
// calculating hit, avo, crit
// in interactor : check status (if dead, set spot to null and save dead character, and spot with null) (if alive, save spot with character
public class BattleService {

    private CharacterDevelopmentService mCharacterDevelopmentService;

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
                    addWeaponAndMoneyFromEnemy(defender, itemsConvoy);

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
            addWeaponAndMoneyFromEnemy(defender, itemsConvoy);
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

    public boolean isDoubleAttack(BaseCharacter attacker, BaseCharacter defender) {
        return ( attacker.getStats().get(StatsType.SPEED).getValue()
                - defender.getStats().get(StatsType.SPEED).getValue() ) >= 5;
    }

    public void calculateDamageAndSetRemainingHp(BaseCharacter attackingCharacter, BaseCharacter defendingCharacter) {

        int howMuchDamageDealt = attackingCharacter.getCharacterBattleStats().getAttack()
                - defendingCharacter.getStats().get(StatsType.DEFENSE).getValue();

        defendingCharacter.setRemainingHealth(defendingCharacter.getRemainingHealth() - howMuchDamageDealt);

        if (defendingCharacter.getRemainingHealth() <= 0) {
            defendingCharacter.setCharacterState(CharacterState.DEAD);
        }
    }

    public void gettingExpAndMoney(BaseCharacter getExperience, BaseCharacter checkIfDead, ItemsConvoy itemsConvoy) {

        if(checkIfDead.getCharacterState().equals(CharacterState.DEAD)) {
            mCharacterDevelopmentService.increaseExpDead(getExperience,checkIfDead);
            addWeaponAndMoneyFromEnemy(checkIfDead, itemsConvoy);

        }
        else {
            mCharacterDevelopmentService.increaseExpNotDead(getExperience,checkIfDead);
        }
    }

    public void addWeaponAndMoneyFromEnemy(BaseCharacter deadCharacter, ItemsConvoy itemsConvoy) {
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
