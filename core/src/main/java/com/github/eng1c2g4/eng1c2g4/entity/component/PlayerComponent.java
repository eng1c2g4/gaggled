package com.github.eng1c2g4.eng1c2g4.entity.component;

import com.badlogic.ashley.core.Component;
import com.github.eng1c2g4.eng1c2g4.event.Event;
import com.github.eng1c2g4.eng1c2g4.event.EventListener;
import java.util.concurrent.atomic.AtomicInteger;

public record PlayerComponent(
        AtomicInteger socialBattery
) implements Component {

    private static final int MAX_SOCIAL_BATTERY = 100;

    public static final PlayerComponent INSTANCE = new PlayerComponent(new AtomicInteger(MAX_SOCIAL_BATTERY));

    public int decrease(int amount) {
        int newSocialBattery = this.socialBattery.updateAndGet(current -> current - amount);
        Event.SocialBatteryDecreased event = new Event.SocialBatteryDecreased(amount, newSocialBattery);
        for (EventListener<Event.SocialBatteryDecreased> listener : Event.SocialBatteryDecreased.LISTENERS) {
            listener.handle(event);
        }
        return newSocialBattery;
    }

}
