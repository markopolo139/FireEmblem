package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.StatEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.StatModel;

import java.util.HashMap;
import java.util.List;

public interface StatConverter {

    Stat convertEntityToStat(StatEmbeddable statEmbeddable);
    StatEmbeddable convertToEntity(Stat stat);

    HashMap<StatsType,Stat> convertEntityListToHashMap(List<StatEmbeddable> statEmbeddables);
    List<StatEmbeddable> convertListToEntity(List<Stat> stats);

    Stat convertModelToStat(StatModel statModel);
    StatModel convertToModel(Stat stat);

    HashMap<StatsType, Stat> convertModelListToHashMap(List<StatModel> statModels);
    List<StatModel> convertListToModel(List<Stat> stats);

    StatEmbeddable convertModelToEntity(StatModel statModel);
    StatModel convertEntityToModel(StatEmbeddable statEmbeddable);

    List<StatEmbeddable> convertModelListToEntity(List<StatModel> statModels);
    List<StatModel> convertEntityListToModel(List<StatEmbeddable> statEmbeddables);

}
