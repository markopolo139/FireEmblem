package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotRepository extends JpaRepository<SpotEntity, Long> {

    Optional<SpotEntity> findByHeightAndWidth(int height, int width);

    List<SpotEntity> findByCharacterIdNotNull();

    List<SpotEntity> findByEnemyIdNotNull();
}