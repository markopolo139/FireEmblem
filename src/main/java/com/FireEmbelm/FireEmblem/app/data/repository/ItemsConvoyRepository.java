package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsConvoyRepository extends JpaRepository<ItemsConvoyEntity, Long> {
    ItemsConvoyEntity findByMoneyAndGameId_GameId(int money, Long id);

    @Modifying
    void deleteByGameId_GameId(Long id);
}
