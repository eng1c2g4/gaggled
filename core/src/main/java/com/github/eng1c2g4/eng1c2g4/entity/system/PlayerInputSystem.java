package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.VelocityComponent;

public final class PlayerInputSystem extends IteratingSystem {

    private static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<VelocityComponent> VELOCITY = ComponentMapper.getFor(VelocityComponent.class);

    public PlayerInputSystem(){
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocityComponent = VELOCITY.get(entity);
        Vector2 acceleration = velocityComponent.acceleration();
        Vector2 velocity = velocityComponent.velocity(); //I don't like that these are instantiated in 2 different ways
        Vector2 position = POSITION.get(entity).position();

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //velocity.y += acceleration.y * deltaTime;
            velocity.y += 5;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //velocity.x -= acceleration.x * deltaTime;
            velocity.x -= 5;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //velocity.y -= acceleration.y * deltaTime;
            velocity.y -= 5;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //velocity.x += acceleration.y * deltaTime;
            velocity.x += 5;

        }
    }
}
