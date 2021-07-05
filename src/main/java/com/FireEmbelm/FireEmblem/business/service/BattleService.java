package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.exceptions.NoWeaponException;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

//TODO : test
// in future include boss to calculating exp
// in interactor : check status (if dead, set spot to null and save dead character, and spot with null) (if alive, save spot with character
public class BattleService {

    private CharacterDevelopmentService mCharacterDevelopmentService;

    public CharacterDevelopmentService getCharacterDevelopmentService() {
        return mCharacterDevelopmentService;
    }

    public BattleService(CharacterDevelopmentService characterDevelopmentService) {
        mCharacterDevelopmentService = characterDevelopmentService;
    }

    public void initialiseBattle(Spot attackerSpot, Spot defenderSpot) throws NoWeaponException, OutOfRangeException {

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
                calculateExperience(defender,attacker);
                mCharacterDevelopmentService.increaseWeaponProgress(defender);

            }
            else {
                if(isDoubleAttack(attacker,defender)) {

                    calculateDamageAndSetRemainingHp(attacker,defender);
                    calculateExperience(attacker,defender);
                }
            }
        }
        else {
            mCharacterDevelopmentService.increaseExpDead(attacker,defender);
        }

        mCharacterDevelopmentService.increaseWeaponProgress(attacker);

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

    public void calculateExperience(BaseCharacter getExperience, BaseCharacter checkIfDead) {

        if(checkIfDead.getCharacterState().equals(CharacterState.DEAD)) {
            mCharacterDevelopmentService.increaseExpDead(getExperience,checkIfDead);
        }
        else {
            mCharacterDevelopmentService.increaseExpNotDead(getExperience,checkIfDead);
        }
    }
}
