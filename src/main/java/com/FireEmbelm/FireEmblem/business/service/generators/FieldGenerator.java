package com.FireEmbelm.FireEmblem.business.service.generators;

import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.business.value.field.SpotsType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldGenerator {

    private static final int NUMBER_OF_PROBABILITIES = 20;
    private static final int RANDOM_HEIGHT = 8;
    private static final int RANDOM_WIDTH = 9;

    private final Random mRandomNumber = new Random();
    private List<SpotsType> mSpotsTypeProbabilities;

    public List<Spot> generateNewField(Integer height, Integer width) {

         if (height == null)
             height = 10 + mRandomNumber.nextInt(RANDOM_HEIGHT);

         if (width == null)
            width = 10 + mRandomNumber.nextInt(RANDOM_WIDTH);

         List<Spot> field = new ArrayList<>();

         for(int i = 1; i < height; i++) {
             for(int j = 1; j < width; j++){
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
