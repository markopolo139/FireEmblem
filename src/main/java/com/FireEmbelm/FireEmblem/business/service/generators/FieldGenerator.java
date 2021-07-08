package com.FireEmbelm.FireEmblem.business.service.generators;

import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import com.FireEmbelm.FireEmblem.business.value.Field.SpotsType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class FieldGenerator {

    private static final int NUMBER_OF_PROBABILITIES = 22;
    private static final int RANDOM_HEIGHT = 7;
    private static final int RANDOM_WIDTH = 8;

    private final Random mRandomNumber = new Random();
    private  List<SpotsType> mSpotsTypeProbabilities;

    public Collection<Spot> generateNewField() {
         int height = 10 + mRandomNumber.nextInt(RANDOM_HEIGHT);
         int width = 10 + mRandomNumber.nextInt(RANDOM_WIDTH);

         Collection<Spot> field = new ArrayList<>();

         for(int i = 0; i < height; i++) {
             for(int j = 0; j < width; j++){
                 field.add(randomSpotWithPosition(i,j));
             }
         }

         return field;
    }

    private Spot randomSpotWithPosition(int height, int width) {
        return new Spot(
                mSpotsTypeProbabilities.get(mRandomNumber.nextInt(NUMBER_OF_PROBABILITIES)),
                height,
                width,
                null
        );
    }

    private void initialiseSpotsProbabilities() {
        mSpotsTypeProbabilities = new ArrayList<>() {{
           add(SpotsType.GATE);
           add(SpotsType.FORT);
           add(SpotsType.FORT);
           add(SpotsType.FORREST);
           add(SpotsType.FORREST);
           add(SpotsType.FORREST);
           add(SpotsType.FORREST);
           add(SpotsType.FORREST);
           add(SpotsType.GRASS);
           add(SpotsType.GRASS);
           add(SpotsType.GRASS);
           add(SpotsType.GRASS);
           add(SpotsType.GRASS);
           add(SpotsType.GRASS);
           add(SpotsType.PLAIN);
           add(SpotsType.PLAIN);
           add(SpotsType.PLAIN);
           add(SpotsType.PLAIN);
           add(SpotsType.PLAIN);
           add(SpotsType.PLAIN);
           add(SpotsType.PLAIN);
           add(SpotsType.PLAIN);
        }};
    }

    public FieldGenerator() {
        initialiseSpotsProbabilities();
    }
}
