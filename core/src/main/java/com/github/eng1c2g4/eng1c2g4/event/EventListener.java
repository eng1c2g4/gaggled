package com.github.eng1c2g4.eng1c2g4.event;

public interface EventListener<E extends Event> {

    void handle(E event);

}
