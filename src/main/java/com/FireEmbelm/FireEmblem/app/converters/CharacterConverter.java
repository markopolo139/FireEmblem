package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CharacterConverter {

    Character convertToCharacter(CharacterEntity characterEntity);
    CharacterEntity convertToEntity(Character character);
    List<Character> convertListToCharacter(List<CharacterEntity> characterEntities);
    List<CharacterEntity> convertListToEntity(List<Character> characters);

}
