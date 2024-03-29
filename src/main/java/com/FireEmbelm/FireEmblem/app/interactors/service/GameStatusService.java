package com.FireEmbelm.FireEmblem.app.interactors.service;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.EnemyConverter;
import com.FireEmbelm.FireEmblem.app.converters.SpotConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.EnemyRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.EnemyModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameStatusService {

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private EnemyRepository mEnemyRepository;

    @Autowired
    private CharacterConverter mCharacterConverter;

    @Autowired
    private EnemyConverter mEnemyConverter;

    @Autowired
    private SpotRepository mSpotRepository;

    @Autowired
    private SpotConverter mSpotConverter;

    public List<EnemyModel> getAliveEnemies(Long gameId) {

        List<EnemyEntity> enemyEntities =
                mEnemyRepository.findByCharacterStateAndGameId_GameId(CharacterState.ALIVE, gameId);

        return mEnemyConverter.convertEntityListToModel(enemyEntities);
    }

    public List<CharacterModel> getAliveCharacters(Long gameId) {

        List<CharacterEntity> characterEntities =
                mCharacterRepository.findByCharacterStateAndGameId_GameId(CharacterState.ALIVE, gameId);

        return mCharacterConverter.convertEntityListToModel(characterEntities);
    }

    public List<CharacterModel> getNotMovedCharacters(Long gameId) {

        List<CharacterEntity> characterEntities =
                mCharacterRepository.findByMovedFalseAndGameId_GameId(gameId);

        return mCharacterConverter.convertEntityListToModel(characterEntities);
    }

    public boolean autoEndTurn(Long gameId) {
        return getNotMovedCharacters(gameId).size() == 0;
    }

    public boolean isFieldWon(Long gameId) {
        return getAliveEnemies(gameId).size() == 0 && getAliveCharacters(gameId).size() != 0;
    }

    public boolean isPlayerDefeated(Long gameId) {
        return getAliveCharacters(gameId).size() == 0;
    }

    public int maxLevelCharacterInGame(Long gameId) {
        return mCharacterRepository.findFirstByGameId_GameIdOrderByLevelDesc(gameId).level;
    }

    public List<SpotModel> getCurrentField(Long gameId) {
        return mSpotConverter.convertEntityListToModel(mSpotRepository.findAllByGameId_GameId(gameId));
    }
}
