package com.github.eng1c2g4.eng1c2g4.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.github.eng1c2g4.eng1c2g4.entity.component.PickupComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;
import com.github.eng1c2g4.eng1c2g4.entity.component.TextureComponent;
import com.github.eng1c2g4.eng1c2g4.inventory.Item;

public final class ItemEntityFactory {

    public static Entity createItemEntity(Item item, Vector2 position) {
        return new Entity()
                .add(new TextureComponent(item.getType().texture()))
                .add(new PositionComponent(position))
                .add(new PickupComponent(item));
    }

}
