package com.FireEmbelm.FireEmblem.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class UserEntity {

    @Id
    @GeneratedValue
    public Long userId;

    @NaturalId
    public String login;

    @JsonIgnore
    public String password;

    public boolean enabled;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id")
    })
    public Set<String> role;

    @JsonIgnore
    @OneToOne(mappedBy = "login", fetch = FetchType.EAGER)
    public GameEntity gameEntity;

    public UserEntity(
            Long userId, String login, String password, boolean enabled, Set<String> role, GameEntity gameEntity
    ) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
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
