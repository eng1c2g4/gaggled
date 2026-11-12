package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.component.PlayerComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.VelocityComponent;
import java.util.stream.Stream;

public final class PlayerInputSystem extends IteratingSystem {
    private static final float MAX_VELOCITY = 50;
    private static final float ACCELERATION = 5;

    private static final ComponentMapper<VelocityComponent> VELOCITY = ComponentMapper.getFor(VelocityComponent.class);

    public PlayerInputSystem(){
        super(Family.all(PositionComponent.class, VelocityComponent.class, PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Vector2 velocity = VELOCITY.get(entity).velocity();

        // detect key presses
        boolean up = Stream.of(Input.Keys.W, Input.Keys.UP).anyMatch(Gdx.input::isKeyPressed);
        boolean down = Stream.of(Input.Keys.S, Input.Keys.DOWN).anyMatch(Gdx.input::isKeyPressed);
        boolean left = Stream.of(Input.Keys.A, Input.Keys.LEFT).anyMatch(Gdx.input::isKeyPressed);
        boolean right = Stream.of(Input.Keys.D, Input.Keys.RIGHT).anyMatch(Gdx.input::isKeyPressed);

        // handle up and down movement
        if (!(up || down)) velocity.y = 0;
        else if (up && velocity.y <= MAX_VELOCITY) velocity.y += ACCELERATION;
        else if (down && velocity.y >= -MAX_VELOCITY) velocity.y -= ACCELERATION;

        // handle left and right movement
        if (!(left || right)) velocity.x = 0;
        else if (left && velocity.x >= -MAX_VELOCITY) velocity.x -= ACCELERATION;
        else if (right && velocity.x <= MAX_VELOCITY) velocity.x += ACCELERATION;
    }
}
