package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stats;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

import java.util.HashMap;
import java.util.Random;

//TODO : test
// character promote in future
public class CharacterDevelopmentService {

    private final Random mRandom = new Random();

    public void increaseWeaponProgress(BaseCharacter baseCharacter) {

        baseCharacter.getWeaponProgresses().get(baseCharacter.getCurrentEquipedItem().getItemCategory().getName()).setProgress(
                baseCharacter.getWeaponProgresses()
                        .get(baseCharacter.getCurrentEquipedItem().getItemCategory().getName()).getProgress() + 5
        );

        if (
                baseCharacter.getWeaponProgresses()
                .get(baseCharacter.getCurrentEquipedItem().getItemCategory().getName()).getProgress() == 100
        ) {
            baseCharacter.getWeaponProgresses().get(baseCharacter.getCurrentEquipedItem().getItemCategory().getName()).setRank(
                    baseCharacter.getWeaponProgresses()
                            .get(baseCharacter.getCurrentEquipedItem().getItemCategory().getName()).getRank() + 1
            );

            baseCharacter.getWeaponProgresses().get(baseCharacter.getCurrentEquipedItem().getItemCategory().getName())
                    .setProgress(0);
        }
    }

    public void increaseExpNotDead(BaseCharacter characterGetExp, BaseCharacter characterNotDead) {
        int levelDifference = characterNotDead.getLevel() - characterGetExp.getLevel();

        if(levelDifference <= -2) {
            characterGetExp.setExp(characterGetExp.getExp() + Math.max((33 + levelDifference)/3, 1));
        }
        if(levelDifference == -1) {
            characterGetExp.setExp(characterGetExp.getExp() + 10);
        }
        if(levelDifference >= 0) {
            characterGetExp.setExp(characterGetExp.getExp() + (31 + levelDifference)/3);
        }

        if (characterGetExp.getExp() >= 100) {
            characterGetExp.setExp(0);
            levelUp(characterGetExp);
        }
    }

    public void increaseExpDead(BaseCharacter characterGetExp, BaseCharacter characterNotDead) {
        int levelDifference = characterNotDead.getLevel() - characterGetExp.getLevel();

        if(levelDifference <= -2) {
            characterGetExp.setExp(characterGetExp.getExp() + Math.max(26 + (levelDifference * 3), 7));
        }
        if(levelDifference == -1) {
            characterGetExp.setExp(characterGetExp.getExp() + 20);
        }
        if(levelDifference >= 0) {
            characterGetExp.setExp(characterGetExp.getExp() + 20 + (levelDifference * 3));
        }

        if (characterGetExp.getExp() >= 100) {
            characterGetExp.setExp(0);
            levelUp(characterGetExp);
        }
    }

    public void levelUp(BaseCharacter levelUpCharacter) {

        HashMap<StatsType, Stats> classBonusStats = levelUpCharacter.getCharacterClass().getBonusStats();
        int randomNumber;
        int totalIncreaseChance;
        boolean didIncrease = false;

        while(!didIncrease) {
            for(Stats stats : levelUpCharacter.getStats().values()) {
                randomNumber = mRandom.nextInt(101);
                totalIncreaseChance =
                        stats.getChanceToIncrease() + classBonusStats.get(stats.getStatsType()).getChanceToIncrease();
                if(randomNumber <= totalIncreaseChance) {
                    stats.setValue(stats.getValue() + 1);
                    didIncrease = true;
                }
            }
        }

        levelUpCharacter.setLevel(levelUpCharacter.getLevel() + 1);
    }
}
