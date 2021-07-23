package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.SpotConverter;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.FireEmbelm.FireEmblem.business.service.generators.FieldGenerator;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldGeneratorInteractor {

    @Autowired
    private FieldGenerator mFieldGenerator;

    @Autowired
    private SpotRepository mSpotRepository;

    @Autowired
    private SpotConverter mSpotConverter;

    public void generateField() {

        mSpotRepository.deleteAll();

        List<Spot> generatedSpots = mFieldGenerator.generateNewField();

        mSpotRepository.saveAll(mSpotConverter.convertListToEntity(generatedSpots));

    }
}
