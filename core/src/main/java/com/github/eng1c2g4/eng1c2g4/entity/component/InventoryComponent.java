package com.github.eng1c2g4.eng1c2g4.entity.component;

import com.badlogic.ashley.core.Component;
import com.github.eng1c2g4.eng1c2g4.inventory.Inventory;

public record InventoryComponent(Inventory inventory) implements Component {}
