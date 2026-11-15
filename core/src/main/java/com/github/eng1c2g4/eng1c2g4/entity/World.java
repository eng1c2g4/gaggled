package com.github.eng1c2g4.eng1c2g4.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.area.Area;
import com.github.eng1c2g4.eng1c2g4.area.AreaRegistry;
import com.github.eng1c2g4.eng1c2g4.entity.system.AreaEntrySystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.CameraSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.MovementSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.PickupSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.PlayerInputSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.RenderSystem;
import com.github.eng1c2g4.eng1c2g4.event.Event;
import com.github.eng1c2g4.eng1c2g4.FindVector;
import com.github.eng1c2g4.eng1c2g4.MapMask;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.system.*;
import com.github.eng1c2g4.eng1c2g4.inventory.Item;
import com.github.eng1c2g4.eng1c2g4.inventory.ItemType;

public class World {
    private final Engine engine;
    private final FindVector findVector = new FindVector(new MapMask());
    private final GooseFactory gooseFactory;
    private int GooseToSpawn = 24;

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
        engine.addSystem(new PickupSystem());

        this.gooseFactory = new GooseFactory();
        this.player = PlayerFactory.create(engine,new Vector2(0,0));

        engine.addEntity(ItemEntityFactory.createItemEntity(new Item(
                ItemType.builder()
                        .name("Bus Ticket")
                        .texture(new Texture(Gdx.files.internal("items/bus_ticket.png")))
                        .build()
                ), new Vector2(100, 100)
        ));

        engine.addEntity(ItemEntityFactory.createItemEntity(new Item(
                ItemType.builder()
                        .name("Ramen")
                        .texture(new Texture(Gdx.files.internal("items/ramen.png")))
                        .build()
                ), new Vector2(780, 420)
        ));

        engine.addEntity(ItemEntityFactory.createItemEntity(new Item(
                ItemType.builder()
                        .name("Umbrella")
                        .texture(new Texture(Gdx.files.internal("items/umbrella.png")))
                        .build()
                ), new Vector2(150, 600)
        ));
    }
    /**
     *  */
    public void update(float deltaTime){
        engine.update(deltaTime);
        if (GooseToSpawn != 0) {
            GooseToSpawn -= 1;
            Vector2 position = ComponentMapper.getFor(PositionComponent.class).get(player).position();
            Vector2 position_of_spawn = findVector.generatePosition(position, EntityType.GOOSE);
            this.engine.addEntity(this.gooseFactory.create(position_of_spawn));
        }

    }
}
