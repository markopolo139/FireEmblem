package com.FireEmbelm.FireEmblem.app.data.entities.embeddable;

import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class WeaponEmbeddable {

    public String name;
    public int rank;
    public int might;
    public int hit;
    public int avo;
    public int crit;
    public int uses;
    public int range;
    public int worth;

    @Enumerated(EnumType.STRING)
    public WeaponCategory itemCategory;

    public WeaponEmbeddable(
            String name, int rank, int might, int hit, int avo, int crit,
            int uses, int range, int worth, WeaponCategory itemCategory
    ) {
        this.name = name;
        this.rank = rank;
        this.might = might;
        this.hit = hit;
        this.avo = avo;
        this.crit = crit;
        this.uses = uses;
        this.range = range;
        this.worth = worth;
        this.itemCategory = itemCategory;
    }

    private WeaponEmbeddable() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeaponEmbeddable that = (WeaponEmbeddable) o;
        return uses == that.uses && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uses);
    }
}
