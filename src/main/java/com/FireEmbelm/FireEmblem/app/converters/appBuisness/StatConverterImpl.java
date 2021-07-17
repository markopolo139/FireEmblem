package com.FireEmbelm.FireEmblem.app.converters.appBuisness;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.StatEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatConverterImpl implements StatConverter {
    @Override
    public Stat convertToStat(StatEmbeddable statEmbeddable) {
        return new Stat(
                statEmbeddable.statType,
                statEmbeddable.value,
                statEmbeddable.increaseChance
        );
    }

    @Override
    public StatEmbeddable convertToEntity(Stat stat) {
        return new StatEmbeddable(
                stat.getStatsType(),
                stat.getValue(),
                stat.getChanceToIncrease()
        );
    }

    @Override
    public HashMap<StatsType, Stat> convertListToHashMap(List<StatEmbeddable> statEmbeddables) {
        return (HashMap<StatsType, Stat>)
                statEmbeddables.stream().collect(Collectors.toMap(data -> data.statType, this::convertToStat));
    }

    @Override
    public List<StatEmbeddable> convertListToEntity(List<Stat> stats) {
        return stats.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
