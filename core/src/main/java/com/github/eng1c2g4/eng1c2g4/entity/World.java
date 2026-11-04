package com.github.eng1c2g4.eng1c2g4.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.system.MovementSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.PlayerInputSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.RenderSystem;
import com.github.eng1c2g4.eng1c2g4.entity.PlayerFactory;

public class World {
    private final Engine engine;


    public World(){
        engine = new Engine();

        engine.addSystem(new MovementSystem());
        engine.addSystem(new PlayerInputSystem());
        engine.addSystem(new RenderSystem());

        PlayerFactory.create(engine,new Vector2(100,100));

    }
    public void update(float deltaTime){
        engine.update(deltaTime);
    }
}
