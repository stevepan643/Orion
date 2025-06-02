package com.steve.orion.Events.MouseEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;
import com.steve.orion.Events.EventType;

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
