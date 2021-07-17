package com.FireEmbelm.FireEmblem.business.service.generators;

import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.service.CharacterDevelopmentService;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;

import java.util.*;
import java.util.stream.Collectors;

public class EnemyGenerator {

    public static final int BASE_MONEY = 400;
    private final Random mRandom = new Random();
    private final CharacterDevelopmentService mCharacterDevelopmentService = new CharacterDevelopmentService();

    public Collection<Enemy> generateEnemy(List<Spot> field, List<Weapon> allWeapon, int highestLevel) {

        int howManyEnemies = (int) (field.size() / 6.25);
        int randomNumber;
        Collection<Enemy> enemies = new ArrayList<>();
        List<CharacterClass> allowedEnemyClass = Arrays.stream(CharacterClass.values())
                .filter(i -> i != CharacterClass.PRIEST && i != CharacterClass.LORD && !i.isPromotedClass())
                .collect(Collectors.toList());

        while(howManyEnemies > 0) {
            randomNumber = mRandom.nextInt(field.size());

            if(field.get(randomNumber).getCharacterOnSpot() == null && field.get(randomNumber).getHeight() > 3) {

                Enemy enemy = new Enemy(
                        1,
                        0,
                        0,
                        Utils.createStats(
                                1, 10 + mRandom.nextInt(65),
                                1, 5 + mRandom.nextInt(15),
                                1, 5 + mRandom.nextInt(15),
                                1, 10 + mRandom.nextInt(20),
                                1, 5 + mRandom.nextInt(10),
                                1, 3 + mRandom.nextInt(5),
                                1, 5 + mRandom.nextInt(10),
                                1, 5 + mRandom.nextInt(10)
                        ),
                        null,
                        new ArrayList<>(),
                        new HashMap<>(),
                        allowedEnemyClass.get(mRandom.nextInt(allowedEnemyClass.size())),
                        CharacterState.ALIVE,
                        false,
                        null,
                        false,
                        ( (BASE_MONEY + mRandom.nextInt(201) ) / 10) * 10
                );

                field.get(randomNumber).setCharacterOnSpot(enemy);
                setWeaponAndWeaponProgressForEnemy(enemy, allWeapon, highestLevel);
                levelUpEnemies(enemy, highestLevel);

                enemies.add(enemy);

                howManyEnemies--;
            }
        }

        return enemies;
    }

    public void setWeaponAndWeaponProgressForEnemy(Enemy enemy, List<Weapon> weapons, int highestLevel) {

        int enemyWeaponRank = highestLevel / 8;

        List<Weapon> allowedWeapons = weapons.stream()
                .filter(i -> enemy.getCharacterClass().getAllowedWeapons().contains(i.getItemCategory()))
                .filter(i -> i.getRank() <= enemyWeaponRank )
                .collect(Collectors.toList());

        enemy.setCurrentEquipedItem(allowedWeapons.get(mRandom.nextInt(allowedWeapons.size())));

        if(mRandom.nextInt(2) == 0)
            enemy.setDropItem(enemy.getCurrentEquipedItem());

        for(WeaponCategory wc : enemy.getCharacterClass().getAllowedWeapons()) {
            enemy.getWeaponProgresses().put(wc,new WeaponProgress(wc,0, enemyWeaponRank));
        }

        enemy.getEquipment().add(enemy.getCurrentEquipedItem());

    }

    public void levelUpEnemies(Enemy enemy, int highestLevel) {

        int highLv = highestLevel;

        if(highestLevel >= 20) {
            for(int i = 1; i < 20; i++) {
                mCharacterDevelopmentService.levelUp(enemy);
            }

            enemy.setLevel(1);
            enemy.setCharacterClass(enemy.getCharacterClass().getPromoteToClasses().get(0));
            highLv -= 20;
        }

        for(int i = 1; i < highLv; i++) {
            mCharacterDevelopmentService.levelUp(enemy);
        }

        enemy.setRemainingHealth(enemy.getStats().get(StatsType.HEALTH).getValue()
                + enemy.getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue());
    }
}
