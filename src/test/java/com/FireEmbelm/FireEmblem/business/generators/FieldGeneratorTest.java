package com.FireEmbelm.FireEmblem.business.generators;

import com.FireEmbelm.FireEmblem.business.service.generators.FieldGenerator;
import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldGeneratorTest {

    private final FieldGenerator mFieldGenerator = new FieldGenerator();

    @Test
    void testGenerator() {
        Spot[][] field = mFieldGenerator.generateNewField();

        Assertions.assertNotNull(field);
    }
}
