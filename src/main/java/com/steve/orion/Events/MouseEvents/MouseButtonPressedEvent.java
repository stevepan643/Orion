package com.steve.orion.Events.MouseEvents;

import com.steve.orion.Events.EventType;

public class MouseButtonPressedEvent extends MouseButtonEvent {

    public MouseButtonPressedEvent(int button) {
        super(button);
    }

    @Override
    public EventType getEventType() {
        return EventType.MouseButtonPressed;
    }

    @Override
    public String getName() {
        return "MouseButtonPressed";
    }

    @Override
    public String toString() {
        return "MouseButtonPressedEvent: " + button;
    }
}
