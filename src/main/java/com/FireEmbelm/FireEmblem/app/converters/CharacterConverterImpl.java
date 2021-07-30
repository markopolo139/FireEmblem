package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
                                    characterEntity.sealType == null ? new ArrayList<Equipment>() : characterEntity.sealType,
                                    characterEntity.statUpType == null ? new ArrayList<Equipment>() : characterEntity.statUpType
                    ).flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new)),
                    mWeaponProgressConverter.convertEntityListToHashMap(characterEntity.weaponProgress),
                    characterEntity.characterClass,
                    characterEntity.characterState,
                    characterEntity.moved
            );

            if(characterEntity.currentEquippedItemId !=null) {
                character.setCurrentEquippedItem(character.getEquipment().get(characterEntity.currentEquippedItemId));
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
                !character.getEquipment().contains(character.getCurrentEquippedItem()) ? null
                        : character.getEquipment().indexOf(character.getCurrentEquippedItem()),
                character.getCharacterClass(),
                character.getCharacterState(),
                character.isMoved(),
                mWeaponConverter.convertListToEntity(character.getEquipment().stream()
                        .filter(i -> i instanceof Weapon)
                        .map(i -> (Weapon) i)
                        .collect(Collectors.toList())
                ),
                mHealingItemConverter.convertListToEntity(character.getEquipment().stream()
                        .filter(i -> i instanceof HealingItemWithUses)
                        .map(i -> (HealingItemWithUses) i)
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

    @Override
    public Character convertModelToCharacter(CharacterModel characterModel) {

        if (characterModel == null)
            return null;

        Character character = new Character(
                characterModel.name,
                characterModel.level,
                characterModel.exp,
                characterModel.remainingHealth,
                mStatConverter.convertModelListToHashMap(characterModel.stats),
                null,
                Stream.of(
                        mWeaponConverter.convertModelListToWeapon(characterModel.weapons),
                        mHealingItemConverter.convertModelListToHealingItem(characterModel.healingItems),
                        characterModel.seals == null ? new ArrayList<Equipment>() : characterModel.seals,
                        characterModel.statsUpItems == null ? new ArrayList<Equipment>() : characterModel.statsUpItems
                ).flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new)),
                mWeaponProgressConverter.convertModelListToHashMap(characterModel.weaponProgress),
                CharacterClass.valueOf(characterModel.characterClass),
                CharacterState.valueOf(characterModel.characterState),
                characterModel.moved
        );

        if(characterModel.currentEquippedItemId != null) {
            character.setCurrentEquippedItem(character.getEquipment().get(characterModel.currentEquippedItemId));
            character.getCharacterBattleStats().calculateBattleStats(character);
        }

        return character;

    }

    @Override
    public CharacterModel convertToModel(Character character) {

        if (character == null)
            return null;

        return new CharacterModel(
                character.getName(),
                character.getLevel(),
                character.getExp(),
                character.getRemainingHealth(),
                mStatConverter.convertListToModel(new ArrayList<>(character.getStats().values())),
                !character.getEquipment().contains(character.getCurrentEquippedItem()) ? null
                        : character.getEquipment().indexOf(character.getCurrentEquippedItem()),
                mWeaponConverter.convertListToModel(
                        character.getEquipment().stream().filter(i -> i instanceof Weapon)
                                .map( i -> (Weapon) i).collect(Collectors.toList())
                ),
                mHealingItemConverter.convertListToModel(
                        character.getEquipment().stream().filter(i -> i instanceof HealingItemWithUses)
                                .map( i -> (HealingItemWithUses) i).collect(Collectors.toList())
                ),
                character.getEquipment().stream().filter(i -> i instanceof Seals)
                        .map( i -> (Seals) i).collect(Collectors.toList()),
                character.getEquipment().stream().filter(i -> i instanceof StatsUpItems)
                        .map( i -> (StatsUpItems) i).collect(Collectors.toList()),
                mWeaponProgressConverter.convertListToModel(new ArrayList<>(character.getWeaponProgresses().values())),
                character.getCharacterClass().name(),
                character.getCharacterState().name(),
                character.isMoved()
        );

    }

    @Override
    public List<Character> convertModelListToCharacter(List<CharacterModel> characterModel) {
        return characterModel.stream().map(this::convertModelToCharacter).collect(Collectors.toList());
    }

    @Override
    public List<CharacterModel> convertListToModel(List<Character> character) {
        return character.stream().map(this::convertToModel).collect(Collectors.toList());
    }

    @Override
    public CharacterEntity convertModelToEntity(CharacterModel characterModel) {

        if (characterModel == null)
            return null;

        return new CharacterEntity(
                null,
                characterModel.name,
                characterModel.level,
                characterModel.exp,
                characterModel.remainingHealth,
                characterModel.currentEquippedItemId,
                CharacterClass.valueOf(characterModel.characterClass),
                CharacterState.valueOf(characterModel.characterState),
                characterModel.moved,
                mWeaponConverter.convertModelListToEntity(characterModel.weapons),
                mHealingItemConverter.convertModelListToEntity(characterModel.healingItems),
                characterModel.seals,
                characterModel.statsUpItems,
                mStatConverter.convertModelListToEntity(characterModel.stats),
                mWeaponProgressConverter.convertModelListToEntity(characterModel.weaponProgress)
        );
    }

    @Override
    public CharacterModel convertEntityToModel(CharacterEntity characterEntity) {

        if (characterEntity == null)
            return null;

        return new CharacterModel(
                characterEntity.name,
                characterEntity.level,
                characterEntity.exp,
                characterEntity.remainingHealth,
                mStatConverter.convertEntityListToModel(characterEntity.stats),
                characterEntity.currentEquippedItemId,
                mWeaponConverter.convertEntityListToModel(characterEntity.weapons),
                mHealingItemConverter.convertEntityListToModel(characterEntity.healingItems),
                characterEntity.sealType,
                characterEntity.statUpType,
                mWeaponProgressConverter.convertEntityListToModel(characterEntity.weaponProgress),
                characterEntity.characterClass.name(),
                characterEntity.characterState.name(),
                characterEntity.moved
        );
    }

    @Override
    public List<CharacterEntity> convertModelListToEntity(List<CharacterModel> characterModels) {
        return characterModels.stream().map(this::convertModelToEntity).collect(Collectors.toList());
    }

    @Override
    public List<CharacterModel> convertEntityListToModel(List<CharacterEntity> characterEntities) {
        return characterEntities.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

}
