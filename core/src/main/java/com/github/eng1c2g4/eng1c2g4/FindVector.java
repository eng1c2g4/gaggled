package com.github.eng1c2g4.eng1c2g4;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.EntityType;

public class FindVector {
    private final MapMask mask;

    public FindVector(MapMask mask) {
        this.mask = mask;
    }

    /**
     * Returns the distance that the entity spawns from the player.
     * @param entity the type of spawned entity.
     * @return distance from the player.
     */
    private static float spawnDistance(EntityType entity) {
        float cameraDistance = 100; // placeholder number for diagonal camera radius
        float distance;

        if (entity == EntityType.GOOSE) {
            // diagonal radius of player sight added
            distance = cameraDistance; // make this a function
        } else {
            // Distance within camera range.
            distance = (float)(cameraDistance * (0.2 + Math.random() * 0.6));
        }
        // Combined distance to spawn entity and distance to sprite center.

        return distance + entity.getDistanceToCenter(); // make this a function
    }

    /**
     * Generates the position where the entity shall spawn.
     * For geese, they spawn outside the player camera, for items,
     * they spawn inside the player camera.
     * @param PlayerVector the current position of the player.
     * @param entity the entity type spawning.
     * @return Vector2 the position of the entity created.
     */
    public Vector2 generatePosition(Vector2 PlayerVector, EntityType entity) {
        float distance = spawnDistance(entity);

        //JDB, converted possible_angle to a float
        while (true) {
            float entity_x = PlayerVector.x;
            float entity_y = PlayerVector.y;

            float possible_angle = MathUtils.random(360f);
            entity_x += distance * MathUtils.sinDeg(possible_angle);
            entity_y += distance * MathUtils.cosDeg(possible_angle);

            if (checkPosition(entity_x, entity_y, entity)) {
                // returns the vector of the goose adjusted to how it spawns.
                float bottomOfSprite_y = entity_y - (float) entity.getHeight()/2;
                return new Vector2(entity_x, bottomOfSprite_y);
            }
        }
    }

    /**
     * Checks if the chosen location is available for the entity's vector to spawn.
     * @param x the x component of the vector.
     * @param y the y component of the vector.
     * @return boolean if it is available.
     */
    private boolean checkPosition(float x, float y, EntityType entity) {
        float size = entity.getHeight();
        MapMask.areaCondition area = this.mask.checkArea(x, y, size, size);

        if (area.outOfBounds()) {return false;}
        else if (area.water() && entity == EntityType.ITEM) {
            // This make it so that items priorities or are more likely to spawn on land
            return Math.random() * 2 >= 1;
        } else {return true;}
    }
}