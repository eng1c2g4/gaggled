package com.github.eng1c2g4.eng1c2g4.area;

import com.badlogic.gdx.math.Vector2;

public record Area(String name, Vector2 origin, Vector2 dimensions) {

    public boolean isInArea(Vector2 position) {
        return origin.x < position.x
            && position.x < origin.x + dimensions.x
            && origin.y < position.y
            && position.y < origin.y + dimensions.y;
    }

}
