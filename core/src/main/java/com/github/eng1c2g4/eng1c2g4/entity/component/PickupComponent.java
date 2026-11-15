package com.github.eng1c2g4.eng1c2g4.entity.component;

import com.badlogic.ashley.core.Component;
import com.github.eng1c2g4.eng1c2g4.inventory.Item;

public record PickupComponent(Item item) implements Component {}
