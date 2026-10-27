package com.github.eng1c2g4.eng1c2g4;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.GooseFactory;
import com.github.eng1c2g4.eng1c2g4.entity.system.MovementSystem;
import com.github.eng1c2g4.eng1c2g4.entity.system.RenderSystem;
import org.jspecify.annotations.Nullable;

public final class Main extends Game {

    private @Nullable GooseFactory gooseFactory;
    private @Nullable Engine engine;

    @Override
    public void create() {
        // init
        this.gooseFactory = new GooseFactory();
        this.engine = new PooledEngine();

        // systems
        engine.addSystem(new RenderSystem());
        engine.addSystem(new MovementSystem());

        // goose entity
        engine.addEntity(this.gooseFactory.create(new Vector2()));
    }

    @Override
    public void render() {
        if (this.engine == null) return;
        this.engine.update(0.25f); // todo: actual delta time
    }

    @Override
    public void dispose() {
        super.dispose();
        if (this.gooseFactory != null) this.gooseFactory.dispose();
    }

}
