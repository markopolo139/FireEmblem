package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EnemyRepository extends JpaRepository<EnemyEntity, Long> {

    List<EnemyEntity> findByMovedFalseAndGameId_GameId(Long id);

    List<EnemyEntity> findByCharacterStateAndGameId_GameId(CharacterState characterState, Long id);

    @Modifying
    @Transactional
    void deleteAllByGameId_GameId(Long id);
}
