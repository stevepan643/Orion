package com.steve.orion.event.MouseEvents;

import com.steve.orion.event.BaseEvent;
import com.steve.orion.event.EventCategory;
import com.steve.orion.event.EventType;

public class MouseMovedEvent extends BaseEvent {
    private final float xPos, yPos;

    public float xPos() { return xPos; }
    public float yPos() { return yPos; }

    public MouseMovedEvent(float xPos, float yPos) {
        super();
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public EventType getEventType() {
        return EventType.MouseMoved;
    }

    @Override
    public String getName() {
        return "MouseMoved";
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.Mouse | EventCategory.Input;
    }

    @Override
    public String toString() {
        return "MouseMovedEvent: " + xPos + ", " + yPos;
    }
}
