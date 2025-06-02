package com.steve.orion.Events.MouseEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;
import com.steve.orion.Events.EventType;

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
