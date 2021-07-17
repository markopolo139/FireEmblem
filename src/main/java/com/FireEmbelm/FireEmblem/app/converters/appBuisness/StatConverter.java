package com.FireEmbelm.FireEmblem.app.converters.appBuisness;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.StatEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface StatConverter {

    Stat convertToStat(StatEmbeddable statEmbeddable);
    StatEmbeddable convertToEntity(Stat stat);
    HashMap<StatsType,Stat> convertListToHashMap(List<StatEmbeddable> statEmbeddables);
    List<StatEmbeddable> convertListToEntity(List<Stat> stats);

}
