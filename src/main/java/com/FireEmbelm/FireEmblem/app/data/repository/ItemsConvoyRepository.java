package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ItemsConvoyRepository extends JpaRepository<ItemsConvoyEntity, Long> {
    ItemsConvoyEntity findByMoney(int money);
}
