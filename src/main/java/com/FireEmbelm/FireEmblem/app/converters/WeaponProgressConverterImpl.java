package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeaponProgressConverterImpl implements WeaponProgressConverter {
    @Override
    public WeaponProgress convertEntityToWeaponProgress(WeaponProgressEmbeddable weaponProgressEmbeddable) {
        return new WeaponProgress(
                weaponProgressEmbeddable.weaponType,
                weaponProgressEmbeddable.progress,
                weaponProgressEmbeddable.rank
        );
    }

    @Override
    public WeaponProgressEmbeddable convertToEntity(WeaponProgress weaponProgress) {
        return new WeaponProgressEmbeddable(
                weaponProgress.getWeaponCategory(),
                weaponProgress.getProgress(),
                weaponProgress.getRank()
        );
    }

    @Override
    public HashMap<WeaponCategory, WeaponProgress> convertEntityListToHashMap(List<WeaponProgressEmbeddable> weaponProgressEmbeddables) {
        return (HashMap<WeaponCategory, WeaponProgress>) weaponProgressEmbeddables.stream()
                .collect(Collectors.toMap(data -> data.weaponType, this::convertEntityToWeaponProgress));
    }

    @Override
    public List<WeaponProgressEmbeddable> convertListToEntity(List<WeaponProgress> weaponProgresses) {
        return weaponProgresses.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
