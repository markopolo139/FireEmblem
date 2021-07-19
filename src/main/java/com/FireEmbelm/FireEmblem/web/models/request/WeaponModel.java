package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.web.validation.ValidWeaponCategory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class WeaponModel {

    @NotBlank
    public String name;

    @Min(1)
    @Max(5)
    public int rank;

    @Min(0)
    public int might;

    @Min(0)
    public int hit;

    @NotEmpty
    public int avo;

    @Min(0)
    public int crit;

    @Min(0)
    public int uses;

    @Min(1)
    public int range;

    @Min(0)
    public int worth;

    @ValidWeaponCategory
    public String itemCategory;

    public WeaponModel
            (String name, int rank, int might, int hit, int avo, int crit,
             int uses, int range, int worth, String itemCategory
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeaponModel that = (WeaponModel) o;
        return rank == that.rank
                && might == that.might
                && hit == that.hit
                && avo == that.avo
                && crit == that.crit
                && uses == that.uses
                && range == that.range
                && worth == that.worth
                && Objects.equals(name, that.name)
                && itemCategory == that.itemCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rank, might, hit, avo, crit, uses, range, worth, itemCategory);
    }
}
