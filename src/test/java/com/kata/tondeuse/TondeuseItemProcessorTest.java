package com.kata.tondeuse;

import com.kata.tondeuse.batchprocessing.TondeuseItemProcessor;
import com.kata.tondeuse.dto.Coordinate;
import com.kata.tondeuse.entity.Tondeuse;
import com.kata.tondeuse.enums.DirectionEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TondeuseItemProcessorTest {

    private final TondeuseItemProcessor processor = new TondeuseItemProcessor();

    @Test
    void testProcessor() throws Exception {

        // Créer un objet Coordinate qui correspond à la première ligne
        Coordinate coordinate = new Coordinate(5, 5);

        // Créer un objet Tondeuse qui correspond à une ligne de coordonnée et de direction
        Tondeuse tondeuse = new Tondeuse(1, 2, DirectionEnum.N);

        // Créer un String qui correspond à une ligne d'actions
        String actions = "GAGAGAGAA";

        // Appeler le processor
        Tondeuse processedTondeuse = processor.process(coordinate);
        Assertions.assertNull(processedTondeuse);

        processedTondeuse = processor.process(tondeuse);
        Assertions.assertNull(processedTondeuse);

        processedTondeuse = processor.process(actions);
        Assertions.assertNotNull(processedTondeuse);

    }

}
