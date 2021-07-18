package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CharacterConverter {

    Character convertEntityToCharacter(CharacterEntity characterEntity);
    CharacterEntity convertToEntity(Character character);

    List<Character> convertEntityListToCharacter(List<CharacterEntity> characterEntities);
    List<CharacterEntity> convertListToEntity(List<Character> characters);

    Character convertModelToCharacter(CharacterModel characterModel);
    CharacterModel convertToModel(Character character);

    List<Character> convertModelListToCharacter(List<CharacterModel> characterModel);
    List<CharacterModel> convertListToModel(List<Character> character);

}
