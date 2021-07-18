package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeaponConverterImpl implements WeaponConverter {
    @Override
    public Weapon convertToWeapon(WeaponEmbeddable weaponEmbeddable) {

        if(weaponEmbeddable == null)
            return null;

        return new Weapon(
                weaponEmbeddable.name,
                weaponEmbeddable.rank,
                weaponEmbeddable.might,
                weaponEmbeddable.hit,
                weaponEmbeddable.avo,
                weaponEmbeddable.crit,
                weaponEmbeddable.uses,
                weaponEmbeddable.range,
                weaponEmbeddable.worth,
                weaponEmbeddable.itemCategory
        );
    }

    @Override
    public WeaponEmbeddable convertToEntity(Weapon weapon) {

        if(weapon == null)
            return null;

        return new WeaponEmbeddable(
                weapon.getName(),
                weapon.getRank(),
                weapon.getMight(),
                weapon.getHit(),
                weapon.getAvo(),
                weapon.getCrit(),
                weapon.getUses(),
                weapon.getRange(),
                weapon.getWorth(),
                (WeaponCategory) weapon.getItemCategory()
        );
    }

    @Override
    public List<Weapon> convertListToWeapon(List<WeaponEmbeddable> weaponEmbeddables) {
        return weaponEmbeddables.stream().map(this::convertToWeapon).collect(Collectors.toList());
    }

    @Override
    public List<WeaponEmbeddable> convertListToEntity(List<Weapon> weapons) {
        return weapons.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
