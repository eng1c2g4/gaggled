package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.TextureComponent;

public final class RenderSystem extends EntitySystem {

    private static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);

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
            Texture texture = TEXTURE.get(entity).texture();

            this.batch.draw(texture, position.x, position.y);
        }
        this.batch.end();
    }
}
