package com.FireEmbelm.FireEmblem.business.service.generators;

import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import com.FireEmbelm.FireEmblem.business.value.Field.SpotsType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

//TODO : test
public class FieldGenerator {

    private static final int NUMBER_OF_PROBABILITIES = 16;
    private static final int RANDOM_HEIGHT = 7;
    private static final int RANDOM_WIDTH = 8;

    private final Random mRandomNumber = new Random();
    private Collection<SpotsType> mSpotsTypeProbabilities;

    public Spot[][] generateNewField() {
         int height = 10 + mRandomNumber.nextInt(RANDOM_HEIGHT);
         int width = 10 + mRandomNumber.nextInt(RANDOM_WIDTH);

         Spot[][] field = new Spot[height][width];

         for(int i = 0; i < height; i++) {
             for(int j = 0; j < width; j++){
                 field[i][j] = randomSpotWithPosition(i,j);
             }
         }

         return field;
    }

    private Spot randomSpotWithPosition(int height, int width) {
        return new Spot(
                ((ArrayList<SpotsType>) mSpotsTypeProbabilities).get(mRandomNumber.nextInt(NUMBER_OF_PROBABILITIES)),
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
           add(SpotsType.GRASS);
           add(SpotsType.GRASS);
           add(SpotsType.GRASS);
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
