package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.EnemyRepository;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameStatusService {

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private EnemyRepository mEnemyRepository;

    public List<EnemyEntity> getAliveEnemies(Long gameId) {
        return mEnemyRepository.findByCharacterStateAndGameId_GameId(CharacterState.ALIVE, gameId);
    }

    public List<CharacterEntity> getAliveCharacters(Long gameId) {
        return mCharacterRepository.findByCharacterStateAndGameId_GameId(CharacterState.ALIVE, gameId);
    }

    public List<CharacterEntity> getNotMovedCharacters(Long gameId) {
        return mCharacterRepository.findByMovedFalseAndGameId_GameId(gameId);
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

}
