package com.FireEmbelm.FireEmblem.app.converters.appBuisness;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface WeaponProgressConverter {

    WeaponProgress convertToWeaponProgress(WeaponProgressEmbeddable weaponProgressEmbeddable);
    WeaponProgressEmbeddable convertToEntity(WeaponProgress weaponProgress);
    HashMap<WeaponCategory, WeaponProgress> convertListToHashMap(List<WeaponProgressEmbeddable> weaponProgressEmbeddables);
    List<WeaponProgressEmbeddable> convertListToEntity(List<WeaponProgress> weaponProgresses);

}
