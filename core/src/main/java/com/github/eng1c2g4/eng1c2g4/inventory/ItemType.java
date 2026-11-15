package com.github.eng1c2g4.eng1c2g4.inventory;

import com.badlogic.gdx.graphics.Texture;
import org.jspecify.annotations.Nullable;

/**
 * The definition of an item. This is the static instance in which
 * is re-used across all instances of it.
 *
 * @param name    name of the item
 * @param texture texture of the item
 * @param uses    number of uses the item begins with
 */
public record ItemType(String name, Texture texture, @Nullable ItemInteraction interaction) {

    public static ItemTypeBuilder.NameProvider<ItemTypeBuilder.TextureProvider<ItemTypeBuilder>> builder() {
        return name -> texture -> new ItemTypeBuilder(name, texture);
    }

}
