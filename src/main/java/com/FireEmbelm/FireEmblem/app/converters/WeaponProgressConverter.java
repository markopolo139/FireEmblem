package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponProgressModel;

import java.util.HashMap;
import java.util.List;

public interface WeaponProgressConverter {

    WeaponProgress convertEntityToWeaponProgress(WeaponProgressEmbeddable weaponProgressEmbeddable);
    WeaponProgressEmbeddable convertToEntity(WeaponProgress weaponProgress);

    HashMap<WeaponCategory, WeaponProgress> convertEntityListToHashMap(List<WeaponProgressEmbeddable> weaponProgressEmbeddables);
    List<WeaponProgressEmbeddable> convertListToEntity(List<WeaponProgress> weaponProgresses);

    WeaponProgress convertModelToWeaponProgress(WeaponProgressModel weaponProgressModel);
    WeaponProgressModel convertToModel(WeaponProgress weaponProgress);

    HashMap<WeaponCategory, WeaponProgress> convertModelListToHashMap(List<WeaponProgressModel> weaponProgressModels);
    List<WeaponProgressModel> convertListToModel(List<WeaponProgress> weaponProgresses);

    WeaponProgressEmbeddable convertModelToEntity(WeaponProgressModel weaponProgressModel);
    WeaponProgressModel convertEntityToModel(WeaponProgressEmbeddable weaponProgressEmbeddable);

    List<WeaponProgressEmbeddable> convertModelListToEntity(List<WeaponProgressModel> weaponProgressModels);
    List<WeaponProgressModel> convertEntityListToModel(List<WeaponProgressEmbeddable> weaponProgressEmbeddables);

}
