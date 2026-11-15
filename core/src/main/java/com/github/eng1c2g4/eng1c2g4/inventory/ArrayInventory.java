package com.github.eng1c2g4.eng1c2g4.inventory;

import org.jspecify.annotations.Nullable;

/**
 * A inventory implementation backed using an array.
 */
public class ArrayInventory implements Inventory {

    protected final @Nullable Item[] backend;

    private int count = 0;

    /**
     * Construct a new {@link ArrayInventory} with a provided size.
     *
     * @param size inventory size
     */
    public ArrayInventory(int size) {
        this.backend = new Item[size];
    }

    @Override
    public void refresh() {

    }

    /**
     * Insert an item into the next available slot. A slot is classes as available
     * if the slot is empty.
     *
     * @param item item to insert
     * @return insertion success
     */
    @Override
    public boolean insert(Item item) {
        // place item in next empty slot
        for (int i = 0; i < this.backend.length; i++) {
            if (this.backend[i] != null) continue;
            this.backend[i] = item;
            this.count++;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        return this.count == this.backend.length;
    }

    public @Nullable Item[] getItems() {
        return this.backend;
    }

}
