package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponModel;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponProgressModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

public interface WeaponProgressConverter {

    WeaponProgress convertEntityToWeaponProgress(WeaponProgressEmbeddable weaponProgressEmbeddable);
    WeaponProgressEmbeddable convertToEntity(WeaponProgress weaponProgress);

    HashMap<WeaponCategory, WeaponProgress> convertEntityListToHashMap(List<WeaponProgressEmbeddable> weaponProgressEmbeddables);
    List<WeaponProgressEmbeddable> convertListToEntity(List<WeaponProgress> weaponProgresses);

    WeaponProgress convertModelToWeaponProgress(WeaponProgressModel weaponProgressModel);
    WeaponProgressModel convertToModel(WeaponProgress weaponProgress);

    List<WeaponProgress> convertModelListToWeaponProgress(List<WeaponProgressModel> weaponProgressModels);
    List<WeaponProgressModel> convertListToModel(List<WeaponProgress> weaponProgresses);

}
