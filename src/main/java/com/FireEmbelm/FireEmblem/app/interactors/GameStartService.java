package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.internal.BaseCharacterToCharacterConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.repository.BaseCharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameStartService {

    @Autowired
    private BaseCharacterRepository mBaseCharacterRepository;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private BaseCharacterToCharacterConverter mBaseCharacterToCharacterConverter;

    public void startGame() {

        mCharacterRepository.deleteAll();

        List<CharacterEntity> characterEntities =
                mBaseCharacterToCharacterConverter.convertListToCharacterEntity(mBaseCharacterRepository.findAll());

        characterEntities.forEach(this::giveBasicWeaponProgress);

        mCharacterRepository.saveAll(characterEntities);
    }

    private void giveBasicWeaponProgress(CharacterEntity characterEntity) {
        characterEntity.weaponProgress = new ArrayList<>() {{
            add(new WeaponProgressEmbeddable(WeaponCategory.SWORD, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.AXE, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.LANCE, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.BOW, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.TOME, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.STAFF, 0, 1));
        }};
    }

}
