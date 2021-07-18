package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CharacterConverterImpl implements CharacterConverter {

    @Autowired
    private StatConverter mStatConverter;

    @Autowired
    private WeaponProgressConverter mWeaponProgressConverter;

    @Autowired
    private WeaponConverter mWeaponConverter;

    @Autowired
    private HealingItemConverter mHealingItemConverter;

    @Override
    public Character convertEntityToCharacter(CharacterEntity characterEntity) {
        Character character;

        if (characterEntity == null)
            character = null;

        else {
            character = new Character(
                    characterEntity.name,
                    characterEntity.level,
                    characterEntity.exp,
                    characterEntity.remainingHealth,
                    mStatConverter.convertEntityListToHashMap(characterEntity.stats),
                    null,
                    Stream.of(
                                    mWeaponConverter.convertEntityListToWeapon(characterEntity.weapons),
                                    mHealingItemConverter.convertEntityListToHealingItem(characterEntity.healingItems),
                                    characterEntity.sealType,
                                    characterEntity.statUpType
                    ).flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new)),
                    mWeaponProgressConverter.convertEntityListToHashMap(characterEntity.weaponProgress),
                    characterEntity.characterClass,
                    characterEntity.characterState,
                    characterEntity.moved
            );

            if(characterEntity.currentEquipedItemId !=null) {
                character.setCurrentEquipedItem(character.getEquipment().get(characterEntity.currentEquipedItemId));
                character.getCharacterBattleStats().calculateBattleStats(character);
            }

        }
        return character;
    }

    @Override
    public CharacterEntity convertToEntity(Character character) {

        if (character == null)
            return null;

        return new CharacterEntity(
                null,
                character.getName(),
                character.getLevel(),
                character.getExp(),
                character.getRemainingHealth(),
                character.getEquipment().indexOf(character.getCurrentEquipedItem()),
                character.getCharacterClass(),
                character.getCharacterState(),
                character.isMoved(),
                mHealingItemConverter.convertListToEntity(character.getEquipment().stream()
                        .filter(i -> i instanceof HealingItemWithUses)
                        .map(i -> (HealingItemWithUses) i)
                        .collect(Collectors.toList())
                ),
                mWeaponConverter.convertListToEntity(character.getEquipment().stream()
                        .filter(i -> i instanceof Weapon)
                        .map(i -> (Weapon) i)
                        .collect(Collectors.toList())
                ),
                character.getEquipment().stream()
                        .filter(i -> i instanceof Seals)
                        .map(i -> (Seals) i).collect(Collectors.toList()),
                character.getEquipment().stream()
                        .filter(i -> i instanceof StatsUpItems)
                        .map(i -> (StatsUpItems) i).collect(Collectors.toList()),
                mStatConverter.convertListToEntity(new ArrayList<>(character.getStats().values())),
                mWeaponProgressConverter.convertListToEntity(new ArrayList<>(character.getWeaponProgresses().values()))
        );
    }

    @Override
    public List<Character> convertEntityListToCharacter(List<CharacterEntity> characterEntities) {
        return characterEntities.stream().map(this::convertEntityToCharacter).collect(Collectors.toList());
    }

    @Override
    public List<CharacterEntity> convertListToEntity(List<Character> characters) {
        return characters.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

}
