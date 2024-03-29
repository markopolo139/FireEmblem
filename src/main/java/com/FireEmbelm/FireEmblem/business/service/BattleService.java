package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.exceptions.NoWeaponException;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.business.value.DifficultySettings;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterBattleStats;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;

import java.util.Random;

//TODO :
// front-end inform user if using weapon with last use
public class BattleService {

    private final Random mRandom = new Random();
    private final CharacterDevelopmentService mCharacterDevelopmentService;

    public void initialiseBattle(
            Spot attackerSpot, Spot defenderSpot, ItemsConvoy itemsConvoy, DifficultySettings difficultySettings
    )
            throws NoWeaponException, OutOfRangeException, InvalidSpotException {

        BaseCharacter attacker = attackerSpot.getCharacterOnSpot();
        BaseCharacter defender = defenderSpot.getCharacterOnSpot();

        validateCharactersOnSpots(attacker,defender);

        isDefenderInWeaponRange(attackerSpot,defenderSpot);
        calculateDamageAndSetRemainingHp(attackerSpot,defenderSpot, difficultySettings);

        if(defender.getCharacterState().equals(CharacterState.ALIVE)) {

            mCharacterDevelopmentService.increaseExpNotDead(attacker, defender, difficultySettings);

            if (defender.getCurrentEquippedItem() != null)
                if(defender.getCurrentEquippedItem().getRange() == attacker.getCurrentEquippedItem().getRange()
                        && defender.getCurrentEquippedItem().getItemCategory() instanceof WeaponCategory) {

                    calculateDamageAndSetRemainingHp(defenderSpot, attackerSpot, difficultySettings);
                    getExpAndMoney(defender, attacker, itemsConvoy, difficultySettings);
                    updateWeaponUseAndProgress(defender);

                    if(attacker.getCharacterState().equals(CharacterState.DEAD)) {
                        attacker.setMoved(true);
                        return;
                    }

                }

            if(isDoubleAttack(attacker,defender)) {
                calculateDamageAndSetRemainingHp(attackerSpot, defenderSpot, difficultySettings);
                getExpAndMoney(attacker, defender, itemsConvoy, difficultySettings);
            }

        }
        else {
            mCharacterDevelopmentService.increaseExpDead(attacker, defender, difficultySettings);
            addWeaponAndMoneyFromEnemyToConvoy(defender, itemsConvoy);
        }

        updateWeaponUseAndProgress(attacker);
        attacker.setMoved(true);

    }

    private void validateCharactersOnSpots(BaseCharacter attacker, BaseCharacter defender)
            throws InvalidSpotException, NoWeaponException {

        if(attacker == null || defender == null)
            throw new InvalidSpotException("Can't battle with empty spot");

        if((attacker instanceof Enemy && defender instanceof Enemy)
                || (attacker instanceof Character && defender instanceof Character))
            throw new InvalidSpotException("Can't attack the same type of characters");

        if (attacker.getCurrentEquippedItem() == null)
            throw new NoWeaponException("Can't attack without a weapon");

        if( !(attacker.getCurrentEquippedItem().getItemCategory() instanceof WeaponCategory) )
            throw new NoWeaponException("Can't attack without a weapon");
    }

    public void isDefenderInWeaponRange(Spot attackerSpot, Spot defenderSpot) throws OutOfRangeException {
        int distanceBetweenCharacter = Math.abs(attackerSpot.getHeight() - defenderSpot.getHeight())
                + Math.abs(attackerSpot.getWidth() - defenderSpot.getWidth());

        if(attackerSpot.getCharacterOnSpot().getCurrentEquippedItem().getRange() != distanceBetweenCharacter)
            throw new OutOfRangeException("Attacked character is out of range, with current equipped weapon");
    }

    public void calculateDamageAndSetRemainingHp(Spot attackerSpot, Spot defenderSpot, DifficultySettings difficultySettings) {

        if(didAttackHit(attackerSpot, defenderSpot, difficultySettings)) {

            int howMuchDamageDealt = calculateDamageDealt(attackerSpot, defenderSpot, difficultySettings);

            if(isAttackCrit(
                    attackerSpot.getCharacterOnSpot().getCharacterBattleStats(),
                    defenderSpot.getCharacterOnSpot())
            )
                howMuchDamageDealt *= 3;

            defenderSpot.getCharacterOnSpot().setRemainingHealth(
                    defenderSpot.getCharacterOnSpot().getRemainingHealth() - howMuchDamageDealt
            );

            if (defenderSpot.getCharacterOnSpot().getRemainingHealth() <= 0) {
                defenderSpot.getCharacterOnSpot().setCharacterState(CharacterState.DEAD);
            }

        }
    }

    public int calculateDamageDealt(Spot attackerSpot, Spot defenderSpot, DifficultySettings difficultySettings) {

        int damageDealt;

        if(! attackerSpot.getCharacterOnSpot().getCurrentEquippedItem().getItemCategory().equals(WeaponCategory.TOME)) {
            damageDealt = attackerSpot.getCharacterOnSpot().getCharacterBattleStats().getAttack()
                    - ( defenderSpot.getCharacterOnSpot().getStats().get(StatsType.DEFENSE).getValue()
                    + defenderSpot.getSpotsType().getDefBoost()
                    + defenderSpot.getCharacterOnSpot().getCharacterClass().getBonusStats().get(StatsType.DEFENSE).getValue());
        }
        else {
            damageDealt = attackerSpot.getCharacterOnSpot().getCharacterBattleStats().getAttack()
                    - ( defenderSpot.getCharacterOnSpot().getStats().get(StatsType.RESISTANCE).getValue()
                    + defenderSpot.getCharacterOnSpot().getCharacterClass().getBonusStats().get(StatsType.RESISTANCE).getValue());
        }

        return (int) (attackerSpot.getCharacterOnSpot() instanceof Enemy ?
                damageDealt * difficultySettings.getEnemyDamageBoost()
                : damageDealt * difficultySettings.getEnemyDamageReceived());
    }

    public boolean didAttackHit(Spot attackerSpot, Spot defenderSpot, DifficultySettings difficultySettings) {

        int hitRate = (int) (attackerSpot.getCharacterOnSpot().getCharacterBattleStats().getHitRate() *
                        (attackerSpot.getCharacterOnSpot() instanceof Enemy ? difficultySettings.getEnemyHitBoost() : 1));

        int avoRate = (int) (defenderSpot.getCharacterOnSpot().getCharacterBattleStats().getAvoid() *
                         (defenderSpot.getCharacterOnSpot() instanceof Enemy ? difficultySettings.getEnemyAvoBoost() : 1));

        int hitPercent = hitRate - avoRate - defenderSpot.getSpotsType().getAvoBoost();

        return hitPercent >= mRandom.nextInt(101);
    }

    public boolean isAttackCrit(CharacterBattleStats attackerStats, BaseCharacter defender) {
        return (attackerStats.getCritical() - defender.getStats().get(StatsType.LUCK).getValue())
                >= mRandom.nextInt(101);
    }

    public boolean isDoubleAttack(BaseCharacter attacker, BaseCharacter defender) {
        return ( (attacker.getStats().get(StatsType.SPEED).getValue()
                + attacker.getCharacterClass().getBonusStats().get(StatsType.SPEED).getValue())
                - (defender.getStats().get(StatsType.SPEED).getValue()
                + defender.getCharacterClass().getBonusStats().get(StatsType.SPEED).getValue() )) >= 5;
    }

    public void getExpAndMoney(
            BaseCharacter getExperienceCharacter, BaseCharacter checkIfDeadCharacter, ItemsConvoy itemsConvoy,
            DifficultySettings difficultySettings
    ) {

        if(checkIfDeadCharacter.getCharacterState().equals(CharacterState.DEAD)) {
            mCharacterDevelopmentService.increaseExpDead(getExperienceCharacter,checkIfDeadCharacter, difficultySettings);
            addWeaponAndMoneyFromEnemyToConvoy(checkIfDeadCharacter, itemsConvoy);

        }
        else {
            mCharacterDevelopmentService.increaseExpNotDead(getExperienceCharacter,checkIfDeadCharacter, difficultySettings);
        }
    }

    public void addWeaponAndMoneyFromEnemyToConvoy(BaseCharacter deadCharacter, ItemsConvoy itemsConvoy) {
        if(deadCharacter instanceof Enemy) {

            itemsConvoy.setMoney(itemsConvoy.getMoney() + ((Enemy) deadCharacter).getGoldDrop());

            if(((Enemy) deadCharacter).getDropItem() != null)
                itemsConvoy.getPlayerItems().add(((Enemy) deadCharacter).getDropItem());

        }
    }

    public void updateWeaponUseAndProgress(BaseCharacter baseCharacter) {

        if(baseCharacter.getCharacterState().equals(CharacterState.DEAD)) {
            return;
        }

        mCharacterDevelopmentService.increaseWeaponProgress(baseCharacter);
        baseCharacter.getCurrentEquippedItem().setUses(baseCharacter.getCurrentEquippedItem().getUses() - 1);
        if (baseCharacter.getCurrentEquippedItem().getUses() == 0) {
            baseCharacter.setCurrentEquippedItem(null);
        }
    }

    public BattleService(CharacterDevelopmentService characterDevelopmentService) {
        mCharacterDevelopmentService = characterDevelopmentService;
    }
}
