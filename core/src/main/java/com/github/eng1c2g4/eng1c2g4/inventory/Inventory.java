package com.github.eng1c2g4.eng1c2g4.inventory;

/**
 * An inventory, an ordered list of item instances to be interacted
 * with by a entity.
 */
public interface Inventory {

    /**
     * Trigger a refresh on this inventory. This will update
     * its contents to make sure they're up-to-date.
     */
    void refresh();

    /**
     * Attempt to insert an item into the inventory.
     *
     * @param item item to insert
     * @return whether the insertion was successful
     */
    boolean insert(Item item);

    boolean isFull();

}
