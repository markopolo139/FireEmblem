package com.FireEmbelm.FireEmblem.business.generators;

import com.FireEmbelm.FireEmblem.business.service.generators.FieldGenerator;
import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class FieldGeneratorTest {

    private final FieldGenerator mFieldGenerator = new FieldGenerator();

    //DO manual debug to test
    @Test
    void testGenerator() {
        Collection<Spot> field = mFieldGenerator.generateNewField();

        Assertions.assertNotNull(field);
    }
}
