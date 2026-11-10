package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;


public final class CameraSystem extends EntitySystem {
    private OrthographicCamera camera;
    public void addedToEngine(Engine engine){
        this.camera = new OrthographicCamera(50,50);
    }

    public void update(float deltaTime, Entity entity){
        Vector2 position = ComponentMapper.getFor(PositionComponent.class).get(entity).position();
        camera.position.set(position.x,position.y,0);

        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2f, 100 - camera.viewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2f, 100 - camera.viewportHeight / 2f);
        camera.update();
    }

}
