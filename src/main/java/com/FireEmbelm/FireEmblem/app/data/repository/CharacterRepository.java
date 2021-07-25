package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    Optional<CharacterEntity> findByNameAndGameId(String name, Long id);

    List<CharacterEntity> findByMovedFalseAndGameId(Long id));

    List<CharacterEntity> findByCharacterStateAndGameId(CharacterState characterState, Long id));

    CharacterEntity findFirstByGameIdOrderByLevelDesc(Long id));

}
