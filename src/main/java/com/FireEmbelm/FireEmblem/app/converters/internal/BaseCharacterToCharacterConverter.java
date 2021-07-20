package com.FireEmbelm.FireEmblem.app.converters.internal;

import com.FireEmbelm.FireEmblem.app.data.entities.BaseCharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BaseCharacterToCharacterConverter {

    CharacterEntity convertToCharacterEntity(BaseCharacterEntity baseCharacterEntity);

    List<CharacterEntity> convertListToCharacterEntity(List<BaseCharacterEntity> baseCharacterEntities);

}
