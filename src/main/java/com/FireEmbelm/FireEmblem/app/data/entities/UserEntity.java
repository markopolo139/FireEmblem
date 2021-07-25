package com.FireEmbelm.FireEmblem.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class UserEntity {

    @Id
    public String login;

    @JsonIgnore
    public String password;

    public boolean enabled;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "game_id")
    })
    public Set<String> userRoles;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "login")
    public GameEntity gameEntity;

    public UserEntity(String login, String password, boolean enabled, Set<String> userRoles, GameEntity gameEntity) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.userRoles = userRoles;
        this.gameEntity = gameEntity;
    }

    protected UserEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
