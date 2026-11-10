package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.github.eng1c2g4.eng1c2g4.entity.component.AnimatedComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.ScaledComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.TextureComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.VelocityComponent;

public final class RenderSystem extends EntitySystem {

    private static final float ANIMATION_VELOCITY_THRESHOLD = 1f;

    private static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<VelocityComponent> VELOCITY = ComponentMapper.getFor(VelocityComponent.class);
    private static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);
    private static final ComponentMapper<AnimatedComponent> ANIMATED = ComponentMapper.getFor(AnimatedComponent.class);
    private static final ComponentMapper<ScaledComponent> SCALED = ComponentMapper.getFor(ScaledComponent.class);
    private final Batch batch = new SpriteBatch();

    private ImmutableArray<Entity> entities = new ImmutableArray<>(Array.with());

    @Override
    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(Family.all(PositionComponent.class, TextureComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {
        this.batch.begin();
        for (Entity entity : this.entities) {
            Vector2 position = POSITION.get(entity).position();

            TextureRegion texture = null;
            // animate if the entity has an animation component
            if (ANIMATED.has(entity) && VELOCITY.has(entity)) {
                AnimatedComponent animated = ANIMATED.get(entity);
                Vector2 velocity = VELOCITY.get(entity).velocity();
                float time = animated.time().updateAndGet(t -> t + deltaTime);
                if (velocity.y < -ANIMATION_VELOCITY_THRESHOLD) texture = animated.down().getKeyFrame(time, true);
                else if (velocity.y > ANIMATION_VELOCITY_THRESHOLD) texture = animated.up().getKeyFrame(time, true);
                else if (velocity.x < -ANIMATION_VELOCITY_THRESHOLD) texture = animated.left().getKeyFrame(time, true);
                else if (velocity.x > ANIMATION_VELOCITY_THRESHOLD) texture = animated.right().getKeyFrame(time, true);
            }

            if (texture == null) texture = new TextureRegion(TEXTURE.get(entity).texture());

            this.batch.draw(texture, position.x, position.y);
            float width = texture.getRegionWidth();
            if (!SCALED.has(entity)) {
                this.batch.draw(texture, position.x - width / 2, position.y);
                continue;
            }
            float scale = SCALED.get(entity).scale();
            width *= scale;
            this.batch.draw(texture, position.x - width / 2, position.y, texture.getWidth() * scale, texture.getHeight() * scale);
        }
        this.batch.end();


    }
}
