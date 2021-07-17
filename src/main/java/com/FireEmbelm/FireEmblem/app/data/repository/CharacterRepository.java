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

    Optional<CharacterEntity> findByName(String name);

    List<CharacterEntity> findByMovedFalse();

    List<CharacterEntity> findByCharacterState(CharacterState characterState);

    @Query("select max(c.level) from CharacterEntity c")
    int findMaxLevel();

}
