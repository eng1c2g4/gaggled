package com.github.eng1c2g4.eng1c2g4;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;                                          // new
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.GooseFactory;
import com.github.eng1c2g4.eng1c2g4.entity.npc_spawning.FindVector;
import com.github.eng1c2g4.eng1c2g4.entity.npc_spawning.MapMask;
import com.github.eng1c2g4.eng1c2g4.entity.system.MovementSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.RenderSystem;
import org.jspecify.annotations.Nullable;

public final class Main extends Game {

    private @Nullable GooseFactory gooseFactory;
    private @Nullable Engine engine;
    private @Nullable MapMask mapMask;
    private @Nullable FindVector findVector;

    //Temp player position for testing goose spawning
    private final Vector2 playerPosition = new Vector2(200, 200);

    @Override
    public void create() {
        // init
        this.gooseFactory = new GooseFactory();
        this.engine = new PooledEngine();
        this.mapMask = new MapMask();
        this.findVector = new FindVector(mapMask);
        // systems
        engine.addSystem(new RenderSystem());
        engine.addSystem(new MovementSystem());
    }
        // spawns goose
        private void spawnGoose(){
            if (findVector == null || gooseFactory == null || engine == null) return;
            Vector2 spawnPosition = this.findVector.generatePosition(playerPosition, "Goose");
            if (spawnPosition == null){
                Gdx.app.log("Goose", "No spawn position found");
                return;
            }
            this.engine.addEntity(this.gooseFactory.create(spawnPosition));
            Gdx.app.log("Goose", "Spawned Goose");
        }
        //


    @Override
    public void render() {
        if (this.engine == null) return;
        //space bar spawns goose
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) spawnGoose();

        this.engine.update(0.25f); // todo: actual delta time
    }

    @Override
    public void dispose() {
        super.dispose();
        if (this.gooseFactory != null) this.gooseFactory.dispose();
        if (this.mapMask != null) this.mapMask.dispose();
    }

}
