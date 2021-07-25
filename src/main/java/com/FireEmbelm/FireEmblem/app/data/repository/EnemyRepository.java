package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemyRepository extends JpaRepository<EnemyEntity, Long> {

    List<EnemyEntity> findByMovedFalseAndGameId(Long id);

    List<EnemyEntity> findByCharacterStateAndGameId(CharacterState characterState, Long id);
}
