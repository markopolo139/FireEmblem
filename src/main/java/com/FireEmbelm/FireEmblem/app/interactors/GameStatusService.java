package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.appBuisness.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.appBuisness.EnemyConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.EnemyRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameStatusService {

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private EnemyRepository mEnemyRepository;

    public List<EnemyEntity> getAliveEnemies() {
        return mEnemyRepository.findByCharacterState(CharacterState.ALIVE);
    }

    public List<CharacterEntity> getAliveCharacters() {
        return mCharacterRepository.findByCharacterState(CharacterState.ALIVE);
    }

    public List<CharacterEntity> getNotMovedCharacters() {
        return mCharacterRepository.findByMovedFalse();
    }

    public boolean autoEndTurn() {
        return getNotMovedCharacters().size() == 0;
    }

    public boolean isFieldWon() {
        return getAliveEnemies().size() == 0 && getAliveCharacters().size() != 0;
    }

    public boolean isPlayerDefeated() {
        return getAliveCharacters().size() == 0;
    }

}
