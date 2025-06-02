package com.steve.orion.Events.MouseEvents;

import com.steve.orion.Events.EventType;

public class MouseButtonReleasedEvent extends MouseButtonEvent {

    public MouseButtonReleasedEvent(int button) {
        super(button);
    }

    @Override
    public EventType getEventType() {
        return EventType.MouseButtonReleased;
    }

    @Override
    public String getName() {
        return "MouseButtonReleased";
    }

    @Override
    public String toString() {
        return "MouseButtonReleasedEvent: " + button;
    }
}
