package com.github.eng1c2g4.eng1c2g4.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.area.Area;
import com.github.eng1c2g4.eng1c2g4.area.AreaRegistry;
import com.github.eng1c2g4.eng1c2g4.entity.system.AreaEntrySystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.CameraSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.MovementSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.PlayerInputSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.RenderSystem;
import com.github.eng1c2g4.eng1c2g4.event.Event;

public class World {
    private final Engine engine;

    public Entity player;
    /**
     *  */
    public World(){
        AreaRegistry areaRegistry = new AreaRegistry();
        areaRegistry.register(new Area("Test Area", new Vector2(100, 100), new Vector2(100, 100)));

        Event.EnterArea.LISTENERS.add(event -> System.out.println("you have entered area " + event.area().name()));

        engine = new Engine();

        engine.addSystem(new MovementSystem());
        engine.addSystem(new PlayerInputSystem());
        engine.addSystem(new RenderSystem());
        engine.addSystem(new CameraSystem());
        engine.addSystem(new AreaEntrySystem(areaRegistry));

        this.player = PlayerFactory.create(engine,new Vector2(0,0));

    }
    /**
     *  */
    public void update(float deltaTime){
        engine.update(deltaTime);

    }
}
