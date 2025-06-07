package com.steve.orion.event.MouseEvents;

import com.steve.orion.event.BaseEvent;
import com.steve.orion.event.EventCategory;
import com.steve.orion.event.EventType;

public class MouseScrolledEvent extends BaseEvent {
    private final float xOffset, yOffset;

    public float getXOffset() { return xOffset; }
    public float getYOffset() { return yOffset; }

    public MouseScrolledEvent(float xOffset, float yOffset) {
        super();
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Override
    public EventType getEventType() {
        return EventType.MouseScrolled;
    }

    @Override
    public String getName() {
        return "MouseScrolled";
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.Mouse | EventCategory.Input;
    }

    @Override
    public String toString() {
        return "MouseScrolledEvent: " + xOffset + ", " + yOffset;
    }
}
