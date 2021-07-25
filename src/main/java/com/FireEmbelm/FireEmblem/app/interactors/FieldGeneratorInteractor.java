package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.SpotConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.GameEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.GameRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.FireEmbelm.FireEmblem.business.service.generators.FieldGenerator;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldGeneratorInteractor {

    @Autowired
    private FieldGenerator mFieldGenerator;

    @Autowired
    private SpotRepository mSpotRepository;

    @Autowired
    private SpotConverter mSpotConverter;

    @Autowired
    private GameRepository mGameRepository;

    public void generateField(Long gameId) {

        GameEntity gameEntity = mGameRepository.findById(gameId).orElseThrow();

        mSpotRepository.deleteAllByGameId_GameId(gameId);

        List<Spot> generatedSpots = mFieldGenerator.generateNewField();
        List<SpotEntity> generatedSpotEntities = mSpotConverter.convertListToEntity(generatedSpots);
        generatedSpotEntities =
                generatedSpotEntities.stream().peek(i -> i.gameId = gameEntity).collect(Collectors.toList());

        mSpotRepository.saveAll(generatedSpotEntities);

    }
}
