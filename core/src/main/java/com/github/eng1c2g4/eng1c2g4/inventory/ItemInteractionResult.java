package com.github.eng1c2g4.eng1c2g4.inventory;

public sealed interface ItemInteractionResult {

    record Success() implements ItemInteractionResult {}

    record Consume() implements ItemInteractionResult {}

    record Failure(String reason) implements ItemInteractionResult {}

}
