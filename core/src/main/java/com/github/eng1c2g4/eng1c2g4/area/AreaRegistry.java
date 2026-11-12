package com.github.eng1c2g4.eng1c2g4.area;

import com.badlogic.gdx.math.Vector2;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class AreaRegistry {

    private final Set<Area> areas = new HashSet<>();

    public boolean register(Area area) {
        return this.areas.add(area);
    }

    public Set<Area> getIntersectedAreas(Vector2 position) {
        return this.areas.stream()
                .filter(area -> area.isInArea(position))
                .collect(Collectors.toSet());
    }

}
