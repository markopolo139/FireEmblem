package com.FireEmbelm.FireEmblem.app.converters.internal;

import com.FireEmbelm.FireEmblem.app.data.entities.BaseCharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BaseCharacterToCharacterConverterImpl implements BaseCharacterToCharacterConverter{

    @Override
    public CharacterEntity convertToCharacterEntity(BaseCharacterEntity baseCharacterEntity) {
        return new CharacterEntity(
                baseCharacterEntity.baseCharacterId,
                baseCharacterEntity.name,
                baseCharacterEntity.level,
                baseCharacterEntity.exp,
                baseCharacterEntity.remainingHealth,
                baseCharacterEntity.currentEquipedItemId,
                baseCharacterEntity.characterClass,
                baseCharacterEntity.characterState,
                baseCharacterEntity.moved,
                null, null,null,null,
                new ArrayList<>(baseCharacterEntity.stats),
                new ArrayList<>(
                        Arrays.asList(
                                new WeaponProgressEmbeddable(WeaponCategory.AXE, 0, 1),
                                new WeaponProgressEmbeddable(WeaponCategory.SWORD, 0, 1),
                                new WeaponProgressEmbeddable(WeaponCategory.BOW, 0, 1),
                                new WeaponProgressEmbeddable(WeaponCategory.LANCE, 0, 1),
                                new WeaponProgressEmbeddable(WeaponCategory.TOME, 0, 1),
                                new WeaponProgressEmbeddable(WeaponCategory.STAFF, 0, 1)
                        )
                ));
    }

    @Override
    public List<CharacterEntity> convertListToCharacterEntity(List<BaseCharacterEntity> baseCharacterEntities) {
        return baseCharacterEntities.stream().map(this::convertToCharacterEntity).collect(Collectors.toList());
    }

}
