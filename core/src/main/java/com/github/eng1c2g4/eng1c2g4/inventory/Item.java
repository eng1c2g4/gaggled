package com.github.eng1c2g4.eng1c2g4.inventory;

/**
 * An instance of an item. This holds the state for the interactable
 * item that may be held within an inventory.
 */
public final class Item {

    private final ItemType type;

    /**
     * Construct a new item instance from a type.
     *
     * @param type item type
     */
    public Item(ItemType type) {
        this.type = type;
    }

    /**
     * Gets the {@link ItemType} of this item instance.
     *
     * @return item type
     */
    public ItemType getType() {
        return this.type;
    }

}
