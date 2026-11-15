package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.VelocityComponent;

public final class MovementSystem extends IteratingSystem {

    private static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<VelocityComponent> VELOCITY = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocityComponent = VELOCITY.get(entity);
        Vector2 acceleration = velocityComponent.acceleration();
        Vector2 velocity = velocityComponent.velocity();
        Vector2 position = POSITION.get(entity).position();

        // update velocity
        velocity.x += acceleration.x * deltaTime;
        velocity.y += acceleration.y * deltaTime;

        // update position
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
    }

}
