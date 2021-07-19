package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeaponConverterImpl implements WeaponConverter {
    @Override
    public Weapon convertEntityToWeapon(WeaponEmbeddable weaponEmbeddable) {

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
    public List<Weapon> convertEntityListToWeapon(List<WeaponEmbeddable> weaponEmbeddables) {
        return weaponEmbeddables.stream().map(this::convertEntityToWeapon).collect(Collectors.toList());
    }

    @Override
    public List<WeaponEmbeddable> convertListToEntity(List<Weapon> weapons) {
        return weapons.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Weapon convertModelToWeapon(WeaponModel weaponModel) {

        if (weaponModel == null)
            return null;

        return new Weapon(
                weaponModel.name,
                weaponModel.rank,
                weaponModel.might,
                weaponModel.hit,
                weaponModel.avo,
                weaponModel.crit,
                weaponModel.uses,
                weaponModel.range,
                weaponModel.worth,
                WeaponCategory.valueOf(weaponModel.itemCategory)
        );
    }

    @Override
    public WeaponModel convertToModel(Weapon weapon) {

        if (weapon == null)
            return null;

        return new WeaponModel(
                weapon.getName(),
                weapon.getRank(),
                weapon.getMight(),
                weapon.getHit(),
                weapon.getAvo(),
                weapon.getCrit(),
                weapon.getUses(),
                weapon.getRange(),
                weapon.getWorth(),
                weapon.getItemCategory().getName()
        );
    }

    @Override
    public List<Weapon> convertModelListToWeapon(List<WeaponModel> weaponModels) {
        return weaponModels.stream().map(this::convertModelToWeapon).collect(Collectors.toList());
    }

    @Override
    public List<WeaponModel> convertListToModel(List<Weapon> weapons) {
        return weapons.stream().map(this::convertToModel).collect(Collectors.toList());
    }
}
