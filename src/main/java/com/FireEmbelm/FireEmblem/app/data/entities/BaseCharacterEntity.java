package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.StatEmbeddable;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import org.hibernate.annotations.NaturalId;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "base_characters ")
public class BaseCharacterEntity {

    @Id
    @GeneratedValue
    public Long baseCharacterId;

    @NaturalId
    public String name;

    public int level;

    public int exp;

    public int remainingHealth;

    @Nullable
    public Integer currentEquipedItemId;

    @Enumerated(EnumType.STRING)
    public CharacterClass characterClass;

    @Enumerated(EnumType.STRING)
    public CharacterState characterState;

    public boolean moved;

    @ElementCollection
    @CollectionTable(name = "base_character_stats", joinColumns = {
            @JoinColumn(name = "base_character_id")
    })
    public List<StatEmbeddable> stats;

    public BaseCharacterEntity(
            Long baseCharacterId, String name, int level, int exp, int remainingHealth,
            @Nullable Integer currentEquipedItemId, CharacterClass characterClass,
            CharacterState characterState, boolean moved, List<StatEmbeddable> stats
    ) {
        this.baseCharacterId = baseCharacterId;
        this.name = name;
        this.level = level;
        this.exp = exp;
        this.remainingHealth = remainingHealth;
        this.currentEquipedItemId = currentEquipedItemId;
        this.characterClass = characterClass;
        this.characterState = characterState;
        this.moved = moved;
        this.stats = stats;
    }

    protected BaseCharacterEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseCharacterEntity that = (BaseCharacterEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
