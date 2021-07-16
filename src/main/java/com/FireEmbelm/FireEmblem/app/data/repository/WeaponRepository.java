package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.WeaponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponRepository extends JpaRepository<WeaponEntity, Long> {

    List<WeaponEntity> findByQuality(int quality);
}
