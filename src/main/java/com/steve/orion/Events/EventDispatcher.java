package com.steve.orion.Events;

import java.util.function.Function;

public class EventDispatcher {
    private final Event event;

    public EventDispatcher(Event event) {
        this.event = event;
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEvent> boolean dispatch(EventType eventType, Function<T, Boolean> handler) {
        if (event.getEventType() == eventType) {
            boolean handled = handler.apply((T) event);
            ((BaseEvent) event).setHandled(handled);
            return true;
        }
        return false;
    }

}
