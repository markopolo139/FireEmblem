package com.FireEmbelm.FireEmblem.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue
    public Long gameId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    public UserEntity login;

    @OneToMany(mappedBy = "gameId")
    public List<CharacterEntity> characters;

    @OneToMany(mappedBy = "gameId")
    public List<EnemyEntity> enemies;

    @OneToMany(mappedBy = "gameId")
    public List<SpotEntity> field;

    @OneToOne
    @JoinColumn(name = "game_id")
    public ItemsConvoyEntity itemsConvoy;

    public GameEntity(
            Long gameId, UserEntity login, List<CharacterEntity> characters, List<EnemyEntity> enemies,
            List<SpotEntity> field, ItemsConvoyEntity itemsConvoy
    ) {
        this.gameId = gameId;
        this.login = login;
        this.characters = characters;
        this.enemies = enemies;
        this.field = field;
        this.itemsConvoy = itemsConvoy;
    }

    protected GameEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
