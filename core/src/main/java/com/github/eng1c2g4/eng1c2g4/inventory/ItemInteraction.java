package com.github.eng1c2g4.eng1c2g4.inventory;

public interface ItemInteraction {

    ItemInteractionResult interact(Context context);

    record Context() {}
    
}
