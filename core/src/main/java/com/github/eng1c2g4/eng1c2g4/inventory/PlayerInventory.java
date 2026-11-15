package com.github.eng1c2g4.eng1c2g4.inventory;

import org.jspecify.annotations.Nullable;

/**
 * An inventory with the intention of being attached to a player.
 * This inventory has a 'selected' slot in which all interactions
 * the player takes out will utilise.
 */
public final class PlayerInventory extends ArrayInventory {

    private static final int DEFAULT_SIZE = 3;

    private int selection = 0;

    /**
     * Construct a new player inventory with the default size.
     */
    public PlayerInventory() {
        super(DEFAULT_SIZE);
    }

    /**
     * Select a slot in the inventory for future interactions.
     * @param slot slot to select in the inventory
     * @throws IllegalArgumentException if the {@code slot} argument is not within range {@code 0 <= slot < size}.
     */
    public void select(int slot) {
        if (slot < 0 || slot >= this.backend.length)
            throw new IllegalArgumentException("slot out of range (0 <= slot < " + this.backend.length + ")");
        this.selection = slot;
    }

    /**
     * Get the current slot selection.
     * @return selected slot index
     */
    public int getSelection() {
        return this.selection;
    }

    /**
     * Get the current slot selection.
     * @return nullable selected slot item
     */
    public @Nullable Item getSelectedItem() {
        return this.backend[this.selection];
    }

}
