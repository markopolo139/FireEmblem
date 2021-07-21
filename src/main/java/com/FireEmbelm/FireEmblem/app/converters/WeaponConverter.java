package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponModel;

import java.util.List;

public interface WeaponConverter {

    Weapon convertEntityToWeapon(WeaponEmbeddable weaponEmbeddable);
    WeaponEmbeddable convertToEntity(Weapon weapon);

    List<Weapon> convertEntityListToWeapon(List<WeaponEmbeddable> weaponEmbeddables);
    List<WeaponEmbeddable> convertListToEntity(List<Weapon> weapons);

    Weapon convertModelToWeapon(WeaponModel weaponModel);
    WeaponModel convertToModel(Weapon weapon);

    List<Weapon> convertModelListToWeapon(List<WeaponModel> weaponModels);
    List<WeaponModel> convertListToModel(List<Weapon> weapons);

}
