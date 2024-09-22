package com.kata.tondeuse.batchprocessing;

import com.kata.tondeuse.dto.Coordinate;
import com.kata.tondeuse.entity.Tondeuse;

import org.springframework.batch.item.ItemProcessor;

public class TondeuseItemProcessor implements ItemProcessor<Object, Tondeuse> {

    private Coordinate maxCoordinate;
    private Tondeuse tondeuse;

    @Override
    public Tondeuse process(Object item) throws Exception {
        if (item instanceof Coordinate) {
            // Récupérer et stocker les coordonnées maximales
            this.maxCoordinate = (Coordinate) item;
            // On ne retourne rien, car les coordonnées ne sont pas persistées
            return null;
        } else if (item instanceof Tondeuse) {
            // Récupérer et stocker les coordonnées et position d'une tondeuse
            this.tondeuse = (Tondeuse) item;
            // On ne retourne rien, car la tondeuse n'a pas encore subit d'actions
            return null;
        } else if (item instanceof String) {
            // Exécuter les actions d'une tondeuse
            for (char action : ((String) item).toCharArray()) {
                this.tondeuse.move(maxCoordinate, action);
            }
            return tondeuse;
        }
        return null;
    }
}
