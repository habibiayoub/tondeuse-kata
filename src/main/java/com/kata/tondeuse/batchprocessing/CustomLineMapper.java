package com.kata.tondeuse.batchprocessing;

import com.kata.tondeuse.dto.Coordinate;
import com.kata.tondeuse.entity.Tondeuse;
import com.kata.tondeuse.enums.DirectionEnum;
import org.springframework.batch.item.file.LineMapper;

public class CustomLineMapper implements LineMapper<Object> {

    private boolean expectPosition = true;

    @Override
    public Object mapLine(String line, int lineNumber) throws Exception {
        if (lineNumber == 1) {
            // Première ligne, on récupère les coordonnées maximales
            String[] coordinates = line.split(" ");
            Coordinate maxCoordinate = new Coordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
            return maxCoordinate; // On retourne les coordonnées maximales pour traitement
        } else {
            // À partir de la deuxième ligne, alterner entre coordonnées et actions
            if (expectPosition) {
                // Cette ligne contient les positions x, y et la direction
                String[] data = line.split(" ");
                Tondeuse tondeuse = new Tondeuse(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        DirectionEnum.valueOf(data[2]));

                // La prochaine ligne sera les actions
                expectPosition = false;
                return tondeuse;
            } else {
                // Cette ligne contient les actions
                // Revenir aux positions après cette ligne
                expectPosition = true;
                return line.trim();
            }
        }
    }
}
