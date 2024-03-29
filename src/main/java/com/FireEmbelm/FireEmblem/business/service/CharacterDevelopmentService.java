package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
import com.FireEmbelm.FireEmblem.business.value.DifficultySettings;
import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

import java.util.HashMap;
import java.util.Random;

public class CharacterDevelopmentService {

    public static final int LEVEL_CUP = 20;

    private final Random mRandom = new Random();

    public void increaseWeaponProgress(BaseCharacter baseCharacter) {

        ItemCategory currentEquippedItemCategory = baseCharacter.getCurrentEquippedItem().getItemCategory();

        baseCharacter.getWeaponProgresses().get(currentEquippedItemCategory).setProgress(
                baseCharacter.getWeaponProgresses().get(currentEquippedItemCategory).getProgress() + 5
        );

        if (baseCharacter.getWeaponProgresses().get(currentEquippedItemCategory).getProgress() == 100) {

            baseCharacter.getWeaponProgresses().get(currentEquippedItemCategory).setRank(
                    baseCharacter.getWeaponProgresses().get(currentEquippedItemCategory).getRank() + 1
            );

            baseCharacter.getWeaponProgresses().get(currentEquippedItemCategory).setProgress(0);
        }
    }

    public void increaseExpNotDead(
            BaseCharacter characterGetExp, BaseCharacter characterNotDead, DifficultySettings difficultySettings
    ) {
        int levelDifference = characterNotDead.getLevel() - characterGetExp.getLevel();
        int expGained = 0;

        if(levelDifference <= -2)
            expGained = (int) (
                    characterGetExp.getExp() + Math.max((33 + levelDifference)/3, 1) * difficultySettings.getExpGained()
            );

        if(levelDifference == -1)
            expGained = (int) (characterGetExp.getExp() + 10 * difficultySettings.getExpGained());

        if(levelDifference >= 0)
            expGained = (int) (characterGetExp.getExp() + (31 + levelDifference)/3 * difficultySettings.getExpGained());

        characterGetExp.setExp(expGained);

        if (characterGetExp.getExp() >= 100) {
            characterGetExp.setExp(0);
            levelUp(characterGetExp);
        }
    }

    public void increaseExpDead(
            BaseCharacter characterGetExp, BaseCharacter characterNotDead, DifficultySettings difficultySettings
    ) {
        int levelDifference = characterNotDead.getLevel() - characterGetExp.getLevel();
        int expGained = 0;

        if(levelDifference <= -2)
            expGained = (int) (
                    characterGetExp.getExp() + Math.max(26 + (levelDifference * 3), 7) * difficultySettings.getExpGained()
            );

        if(levelDifference == -1)
            expGained = (int) (
                    characterGetExp.getExp() + 20 * difficultySettings.getExpGained()
            );

        if(levelDifference >= 0)
            expGained = (int) (
                    characterGetExp.getExp() + 20 + (levelDifference * 3) * difficultySettings.getExpGained()
            );

        characterGetExp.setExp(expGained);

        if (characterGetExp.getExp() >= 100) {
            characterGetExp.setExp(0);
            levelUp(characterGetExp);
        }
    }

    public void levelUp(BaseCharacter levelUpCharacter) {

        if (levelUpCharacter.getLevel() >= LEVEL_CUP)
            return;

        HashMap<StatsType, Stat> classBonusStats = levelUpCharacter.getCharacterClass().getBonusStats();
        int randomNumber;
        int totalIncreaseChance;
        boolean didIncrease = false;

        while(!didIncrease) {
            for(Stat stat : levelUpCharacter.getStats().values()) {
                randomNumber = mRandom.nextInt(101);
                totalIncreaseChance =
                        stat.getChanceToIncrease() + classBonusStats.get(stat.getStatsType()).getChanceToIncrease();
                if(randomNumber <= totalIncreaseChance) {
                    stat.setValue(stat.getValue() + 1);
                    didIncrease = true;
                }
            }
        }

        levelUpCharacter.setLevel(levelUpCharacter.getLevel() + 1);
    }
}
