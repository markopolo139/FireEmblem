package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotRepository extends JpaRepository<SpotEntity, Long> {

    SpotEntity findByHeightAndWidthAndGameId_GameId(int height, int width, Long id);

    List<SpotEntity> findByCharacterIdNotNullAndGameId_GameId(Long id);

    List<SpotEntity> findByEnemyIdNotNullAndGameId_GameId(Long id);

    @Modifying
    void deleteAllByGameId_GameId(Long id);
}
