package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.web.validation.ValidWeaponCategory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

public class WeaponProgressModel {

    @ValidWeaponCategory
    public String weaponCategory;

    @Min(0)
    public int progress;

    @Min(1)
    @Max(5)
    public int rank;

    public WeaponProgressModel(String weaponCategory, int progress, int rank) {
        this.weaponCategory = weaponCategory;
        this.progress = progress;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeaponProgressModel that = (WeaponProgressModel) o;
        return progress == that.progress && rank == that.rank && Objects.equals(weaponCategory, that.weaponCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponCategory, progress, rank);
    }
}
