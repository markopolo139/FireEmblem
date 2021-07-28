package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ItemsConvoyRepository extends JpaRepository<ItemsConvoyEntity, Long> {

    ItemsConvoyEntity findByGameId_GameId(Long id);

    @Modifying
    @Transactional
    void deleteByGameId_GameId(Long id);
}
