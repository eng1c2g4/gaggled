package com.github.eng1c2g4.eng1c2g4.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.system.CameraSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.MovementSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.PlayerInputSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.RenderSystem;
import com.github.eng1c2g4.eng1c2g4.entity.PlayerFactory;

public class World {
    private final Engine engine;

    public Entity player;
    /**
     *  */
    public World(){
        engine = new Engine();

        engine.addSystem(new MovementSystem());
        engine.addSystem(new PlayerInputSystem());
        engine.addSystem(new RenderSystem());
        engine.addSystem(new CameraSystem());

        this.player = PlayerFactory.create(engine,new Vector2(0,0));

    }
    /**
     *  */
    public void update(float deltaTime){
        engine.update(deltaTime);

    }
}
