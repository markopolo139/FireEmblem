package com.FireEmbelm.FireEmblem.app.data.entities.embeddable;

import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class WeaponProgressEmbeddable {

    @Enumerated(EnumType.STRING)
    public WeaponCategory weaponType;

    public int progress;
    public int rank;

    public WeaponProgressEmbeddable(WeaponCategory weaponType, int progress, int rank) {
        this.weaponType = weaponType;
        this.progress = progress;
        this.rank = rank;
    }

    private WeaponProgressEmbeddable() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeaponProgressEmbeddable that = (WeaponProgressEmbeddable) o;
        return progress == that.progress && rank == that.rank && weaponType == that.weaponType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponType, progress, rank);
    }
}
