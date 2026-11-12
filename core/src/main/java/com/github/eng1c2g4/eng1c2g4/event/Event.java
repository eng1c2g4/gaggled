package com.github.eng1c2g4.eng1c2g4.event;

import com.github.eng1c2g4.eng1c2g4.area.Area;
import java.util.HashSet;
import java.util.Set;

public sealed interface Event {

    record EnterArea(Area area) implements Event {
        public static Set<EventListener<EnterArea>> LISTENERS = new HashSet<>();
    }

}
