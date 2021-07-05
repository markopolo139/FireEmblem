package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;

//TODO : test
// lv up, character promote
// ask where put this
public class CharacterDevelopmentService {
    
    public void increaseWeaponProgress(BaseCharacter baseCharacter) {

        baseCharacter.getWeaponProgresses().get(baseCharacter.getCurrentEquipedItem().getItemCategory()).setProgress(
                baseCharacter.getWeaponProgresses()
                        .get(baseCharacter.getCurrentEquipedItem().getItemCategory()).getProgress() + 5
        );

        if (
                baseCharacter.getWeaponProgresses()
                .get(baseCharacter.getCurrentEquipedItem().getItemCategory()).getProgress() == 100
        ) {
            baseCharacter.getWeaponProgresses().get(baseCharacter.getCurrentEquipedItem().getItemCategory()).setRank(
                    baseCharacter.getWeaponProgresses()
                            .get(baseCharacter.getCurrentEquipedItem().getItemCategory()).getRank() + 1
            );
        }
    }

    public void increaseExpNotDead(BaseCharacter getExp, BaseCharacter notDead) {

    }

    public void increaseExpDead(BaseCharacter getExp, BaseCharacter dead) {

    }

    public void levelUp() {

    }
}
