package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.area.Area;
import com.github.eng1c2g4.eng1c2g4.area.AreaRegistry;
import com.github.eng1c2g4.eng1c2g4.entity.component.PlayerInputComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.event.Event;
import com.github.eng1c2g4.eng1c2g4.event.EventListener;
import java.util.HashSet;
import java.util.Set;

public final class AreaEntrySystem extends IteratingSystem {

    private static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);

    private final Set<Area> inside = new HashSet<>();
    private final AreaRegistry registry;

    public AreaEntrySystem(AreaRegistry registry) {
        super(Family.all(PositionComponent.class, PlayerInputComponent.class).get());
        this.registry = registry;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Vector2 position = POSITION.get(entity).position();
        Set<Area> currentAreas = this.registry.getIntersectedAreas(position);

        Set<Area> newAreas = new HashSet<>(currentAreas);
        newAreas.removeAll(this.inside);
        for (Area area : newAreas) {
            for (EventListener<Event.EnterArea> listener : Event.EnterArea.LISTENERS) {
                listener.handle(new Event.EnterArea(area));
            }
        }

        this.inside.clear();
        this.inside.addAll(currentAreas);
    }
}
