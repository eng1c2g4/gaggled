package com.github.eng1c2g4.eng1c2g4;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.EntityType;

public class FindVector {
    // TODO implement method spriteSize(Entity)
    private static final float GOOSE_SIZE = 32;
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
        float cameraDistance = 100;
        float distance;

        if (entity == EntityType.GOOSE) {
            // Half the length of the player sight,
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
            float enitiy_x = PlayerVector.x;
            float entity_y = PlayerVector.y;

            float possible_angle = MathUtils.random(360f);
            enitiy_x += distance * MathUtils.sinDeg(possible_angle);
            entity_y += distance * MathUtils.cosDeg(possible_angle);

            if (checkPosition(enitiy_x, entity_y, entity)) {
                float cornerOfSprite_x = enitiy_x - (float) entity.getWidth()/2;
                float cornerOfSprite_y = enitiy_x - (float) entity.getHeight()/2;
                return new Vector2(cornerOfSprite_x, cornerOfSprite_y);
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
        float size = GOOSE_SIZE;
        MapMask.areaCondition area = this.mask.checkArea(x, y, size, size);

        if (area.outOfBounds()) {return false;}
        else if (area.water() && entity == EntityType.ITEM) {
            // This make it so that items priorities or are more likely to spawn on land
            return Math.random() * 2 >= 1;
        } else {return true;}
    }
}