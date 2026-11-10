package com.github.eng1c2g4.eng1c2g4.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.github.eng1c2g4.eng1c2g4.entity.component.AnimatedComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.ScaledComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.TextureComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.VelocityComponent;

public final class PlayerFactory implements Disposable {

    private static final TextureComponent textureComponent = new TextureComponent(new Texture(Gdx.files.internal("sprites/student.png")));

    public static Entity create(Engine engine, Vector2 spawnPosition) {
        Entity player = new Entity();
        player.add(textureComponent);
        player.add(new PositionComponent(new Vector2(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f)));
        player.add(new VelocityComponent(new Vector2(), new Vector2()));
        player.add(AnimatedComponent.fromSpriteSheet(new Texture(Gdx.files.internal("sprites/student_walk.png"))));
        player.add(new ScaledComponent(10f));

        engine.addEntity(player);
        return player;
    }



    @Override
    public void dispose() {
        textureComponent.texture().dispose();
    }
}
