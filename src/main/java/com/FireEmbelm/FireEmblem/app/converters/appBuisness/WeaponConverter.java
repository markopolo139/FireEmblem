package com.FireEmbelm.FireEmblem.app.converters.appBuisness;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WeaponConverter {

    Weapon convertToWeapon(WeaponEmbeddable weaponEmbeddable);
    WeaponEmbeddable convertToEntity(Weapon weapon);
    List<Weapon> convertListToWeapon(List<WeaponEmbeddable> weaponEmbeddables);
    List<WeaponEmbeddable> convertListToEntity(List<Weapon> weapons);

}
