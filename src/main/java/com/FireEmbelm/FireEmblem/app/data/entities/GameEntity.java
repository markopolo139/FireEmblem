package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.business.entitie.Character;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue
    public Long gameId;

    public String login;

    public String password;

    @OneToMany
    public List<CharacterEntity> characters;

    @OneToMany
    public List<EnemyEntity> enemies;

    @OneToMany
    public List<SpotEntity> field;

    @OneToOne
    public ItemsConvoyEntity itemsConvoy;

    public GameEntity(
            Long gameId, String login, String password,
            List<CharacterEntity> characters, List<EnemyEntity> enemies,
            List<SpotEntity> field, ItemsConvoyEntity itemsConvoy
    ) {
        this.gameId = gameId;
        this.login = login;
        this.password = password;
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
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
