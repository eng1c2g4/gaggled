package com.github.eng1c2g4.eng1c2g4.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.component.InventoryComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.PickupComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.PlayerComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.inventory.Inventory;
import com.github.eng1c2g4.eng1c2g4.inventory.Item;
import org.jspecify.annotations.Nullable;

public final class PickupSystem extends EntitySystem {

    private static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<PickupComponent> PICK_UP = ComponentMapper.getFor(PickupComponent.class);
    private static final ComponentMapper<InventoryComponent> INVENTORY = ComponentMapper.getFor(InventoryComponent.class);

    private static final int PICKUP_DISTANCE = 15;

    private @Nullable Engine engine;

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        if (this.engine == null) return;
        ImmutableArray<Entity> items = this.engine.getEntitiesFor(Family.all(PickupComponent.class, PositionComponent.class).get());
        Entity player = this.engine.getEntitiesFor(Family.one(PlayerComponent.class, PositionComponent.class, InventoryComponent.class).get()).get(0);

        Inventory inventory = INVENTORY.get(player).inventory();
        if (inventory.isFull()) return; // can't pick up any items


        Vector2 playerPosition = POSITION.get(player).position();
        for (Entity itemEntity : items) {
            Vector2 itemPosition = POSITION.get(itemEntity).position();

            float distanceSquared = playerPosition.dst2(itemPosition);
            if (distanceSquared > Math.pow(PICKUP_DISTANCE, 2)) continue;

            Item item = PICK_UP.get(itemEntity).item();
            if (inventory.insert(item)) this.engine.removeEntity(itemEntity);
        }
    }

    @Override
    public void removedFromEngine(Engine engine) {
        this.engine = null;
    }
}
