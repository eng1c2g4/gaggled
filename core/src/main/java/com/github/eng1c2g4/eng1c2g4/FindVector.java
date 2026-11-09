package com.github.eng1c2g4.eng1c2g4;

import com.badlogic.gdx.math.Vector2;


public class FindVector {

    /**
     * Returns the distance that the entity spawns from the player.
     * @param entity the type of spawned entity.
     * @return distance from the player.
     */
    private static int spawnDistance(String entity) {
        int distance;

        if (entity.equals("Goose")) {
            // Half the length of the player sight,
            distance = 100; // make this a function
        } else {
            // Distance within camera range.
            distance = 100;
        }
        // Combined distance to spawn entity and distance to sprite center.

        return distance + 100; // make this a function
    }

    /**
     * Generates the position where the entity shall spawn.
     * For geese, they spawn outside the player camera, for items,
     * they spawn inside the player camera.
     * @param PlayerVector the current position of the player.
     * @param entity the entity type spawning.
     * @return Vector2 the position of the entity created.
     */
    public static Vector2 generatePosition(Vector2 PlayerVector, String entity) {

        int distance = spawnDistance(entity);


        while (true) {
            float enitiy_x = PlayerVector.x;
            float entity_y = PlayerVector.y;

            int possible_angle = (int)(Math.random() * 361);

            enitiy_x += (float)(distance * Math.sin(possible_angle));
            entity_y += (float)(distance * Math.cos(possible_angle));

            if (checkPosition(enitiy_x, entity_y, entity)) {
                return new Vector2(enitiy_x, entity_y);
            }
        }
    }

    /**
     * Checks if the chosen location is available for the entity's vector to spawn.
     * @param x the x component of the vector.
     * @param y the y component of the vector.
     * @return boolean if it is available.
     */
    private static boolean checkPosition(float x, float y, String entity) {
        /*
        RedBlue redBlue = checkArea(x, y, entity);

        if (redBlue.hasRed()) {
            return false;
        } else if (redBlue.hasBlue() && entity.equals("Item")) {
            // This make it so that items priorities or are more likely to spawn on land.
            return (Math.random() * 2) >= 1;
        } else {
            return true;
        }
        todo:This is current a WIP
         */
        return true;
    }
}