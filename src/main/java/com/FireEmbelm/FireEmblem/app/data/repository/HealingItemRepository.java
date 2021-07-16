package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.HealingItemEntity;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealingItemRepository extends JpaRepository<HealingItemEntity, Long> {

    List<HealingItemEntity> findByHealItemEmbeddableHealType(HealingItems healingItems);

}
