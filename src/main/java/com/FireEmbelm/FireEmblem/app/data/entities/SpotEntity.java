package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.value.field.SpotsType;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "spots")
public class SpotEntity {

    @Id
    @GeneratedValue
    public Long spotId;

    @Enumerated(EnumType.STRING)
    public SpotsType spotType;

    public int height;
    public int width;

    @OneToOne
    @Nullable
    @JoinColumn(name = "character_id")
    public CharacterEntity characterId;

    @OneToOne
    @Nullable
    @JoinColumn(name = "enemy_id")
    public EnemyEntity enemyId;

    public SpotEntity(
            Long spotId, SpotsType spotType, int height, int width,
            @Nullable CharacterEntity characterId, @Nullable EnemyEntity enemyId
    ) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.height = height;
        this.width = width;
        this.characterId = characterId;
        this.enemyId = enemyId;
    }

    private SpotEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpotEntity that = (SpotEntity) o;
        return height == that.height && width == that.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }
}
