package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    Optional<CharacterEntity> findByNameAndGameId_GameId(String name, Long id);

    List<CharacterEntity> findByMovedFalseAndGameId_GameId(Long id);

    List<CharacterEntity> findByCharacterStateAndGameId_GameId(CharacterState characterState, Long id);

    CharacterEntity findFirstByGameId_GameIdOrderByLevelDesc(Long id);

    @Modifying
    @Transactional
    void deleteAllByGameId_GameId(Long id);

}
