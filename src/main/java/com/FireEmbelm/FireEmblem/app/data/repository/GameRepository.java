package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    @Transactional
    @Modifying
    @Query("update GameEntity ge set ge.difficultySetting = :difficulty where ge.gameId < :id")
    void updateDifficultySetting(@Param("difficulty") String difficulty, @Param("id") long id);
}
