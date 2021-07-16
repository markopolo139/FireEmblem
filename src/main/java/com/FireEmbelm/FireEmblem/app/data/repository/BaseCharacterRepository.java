package com.FireEmbelm.FireEmblem.app.data.repository;

import com.FireEmbelm.FireEmblem.app.data.entities.BaseCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCharacterRepository extends JpaRepository<BaseCharacterEntity, Long> {

}
