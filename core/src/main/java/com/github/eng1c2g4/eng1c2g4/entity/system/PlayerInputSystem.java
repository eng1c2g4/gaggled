package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.TextureComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.VelocityComponent;

public final class PlayerInputSystem extends IteratingSystem {
    private static final float MAX_VELOCITY = 50;
    private static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<VelocityComponent> VELOCITY = ComponentMapper.getFor(VelocityComponent.class);
    private static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);

    public PlayerInputSystem(){
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocityComponent = VELOCITY.get(entity);
        TextureComponent textureComponent = TEXTURE.get(entity);
        Vector2 acceleration = velocityComponent.acceleration();
        Vector2 velocity = velocityComponent.velocity();
        Vector2 position = POSITION.get(entity).position();
        Texture texture = textureComponent.texture(); //todo: Animation if we feel like it

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            texture = new Texture("sprites/student_walk.png");
            if (velocity.y <= MAX_VELOCITY) {
                velocity.y += 5;
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            texture = new Texture("sprites/student_walk.png");
            if (velocity.y >= -MAX_VELOCITY) {
                velocity.y -= 5;
            }
        } else {
            if (Math.abs(velocity.y) > 10/* Placeholder */){
                acceleration.y = velocity.y *-0.8f;
            } else {
                texture = new Texture("sprites/student.png");
                acceleration.y = 0;
                velocity.y = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (velocity.x >= -MAX_VELOCITY) {
                velocity.x -= 5;
            }
        }else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (velocity.x <= MAX_VELOCITY) {
                velocity.x += 5;
            }

        } else {
            if (Math.abs(velocity.x) > 10/* Placeholder */){
                acceleration.x = velocity.x *-0.8f;
            } else {
                texture = new Texture("sprites/student.png");
                acceleration.x = 0;
                velocity.x = 0;
            }
        }

    }
}
