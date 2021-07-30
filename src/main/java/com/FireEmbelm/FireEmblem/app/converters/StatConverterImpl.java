package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.StatEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.web.models.request.StatModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatConverterImpl implements StatConverter {
    @Override
    public Stat convertEntityToStat(StatEmbeddable statEmbeddable) {
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
    public HashMap<StatsType, Stat> convertEntityListToHashMap(List<StatEmbeddable> statEmbeddables) {
        return (HashMap<StatsType, Stat>)
                statEmbeddables.stream().collect(Collectors.toMap(data -> data.statType, this::convertEntityToStat));
    }

    @Override
    public List<StatEmbeddable> convertListToEntity(List<Stat> stats) {
        return stats.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Stat convertModelToStat(StatModel statModel) {
        return new Stat(
                StatsType.valueOf(statModel.statType),
                statModel.value,
                statModel.chanceToIncrease
        );
    }

    @Override
    public StatModel convertToModel(Stat stat) {
        return new StatModel(
                stat.getStatsType().name(),
                stat.getValue(),
                stat.getChanceToIncrease()
        );
    }

    @Override
    public HashMap<StatsType, Stat> convertModelListToHashMap(List<StatModel> statModels) {
        return (HashMap<StatsType, Stat>) statModels.stream().map(this::convertModelToStat)
                .collect(Collectors.toMap(Stat::getStatsType, data -> data));
    }

    @Override
    public List<StatModel> convertListToModel(List<Stat> stats) {
        return stats.stream().map(this::convertToModel).collect(Collectors.toList());
    }

    @Override
    public StatEmbeddable convertModelToEntity(StatModel statModel) {
        return new StatEmbeddable(
                StatsType.valueOf(statModel.statType),
                statModel.value,
                statModel.chanceToIncrease
        );
    }

    @Override
    public StatModel convertEntityToModel(StatEmbeddable statEmbeddable) {
        return new StatModel(
                statEmbeddable.statType.name(),
                statEmbeddable.value,
                statEmbeddable.increaseChance
        );
    }

    @Override
    public List<StatEmbeddable> convertModelListToEntity(List<StatModel> statModels) {
        return statModels.stream().map(this::convertModelToEntity).collect(Collectors.toList());
    }

    @Override
    public List<StatModel> convertEntityListToModel(List<StatEmbeddable> statEmbeddables) {
        return statEmbeddables.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

}
