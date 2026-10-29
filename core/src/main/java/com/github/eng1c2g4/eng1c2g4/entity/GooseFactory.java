package com.github.eng1c2g4.eng1c2g4.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.TextureComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.VelocityComponent;

public final class GooseFactory implements Disposable {

    private final TextureComponent textureComponent = new TextureComponent(new Texture(Gdx.files.internal("sprites/goose.png")));

    public Entity create(Vector2 spawnPosition) {
        return new Entity().add(this.textureComponent).add(new PositionComponent(spawnPosition)).add(new VelocityComponent(new Vector2(), new Vector2()));
    }

    @Override
    public void dispose() {
        this.textureComponent.texture().dispose();
    }

}
