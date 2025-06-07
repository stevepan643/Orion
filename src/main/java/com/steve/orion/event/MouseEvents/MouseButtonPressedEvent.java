package com.steve.orion.event.MouseEvents;

import com.steve.orion.event.EventType;

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
