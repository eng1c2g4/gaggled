package com.github.eng1c2g4.eng1c2g4.inventory;

import com.badlogic.gdx.graphics.Texture;
import org.jspecify.annotations.Nullable;

/**
 * A builder for item types.
 */
public final class ItemTypeBuilder {

    private final String name;
    private final Texture texture;

    private @Nullable ItemInteraction interaction;

    /**
     * Constructs a builder with the required {@code name} and
     * {@code texture} properties.
     * @param name name of the item
     * @param texture texture of the item
     */
    ItemTypeBuilder(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
    }

    /**
     * Set the interaction handler for this item.
     * @param interaction interaction handler
     * @return this builder
     */
    public ItemTypeBuilder interaction(ItemInteraction interaction) {
        this.interaction = interaction;
        return this;
    }

    /**
     * Build the item type based on the configured properties.
     * @return new item type
     */
    public ItemType build() {
        return new ItemType(
                this.name,
                this.texture,
                this.interaction
        );
    }

    /**
     * A provider for the name of the item, to be used as a lambda
     * with an {@link ItemTypeBuilder}.
     * @param <T> next provider
     */
    public interface NameProvider<T> {
        /**
         * Set the name of the item.
         * @param name item name
         * @return next provider
         */
        T name(String name);
    }

    /**
     * A provider for the texture of the item, to be used as a
     * lambda with an {@link ItemTypeBuilder}.
     * @param <T> next provider
     */
    public interface TextureProvider<T> {
        /**
         * Set the texture of an item.
         * @param texture texture of the item
         * @return next provider
         */
        T texture(Texture texture);
    }

}
