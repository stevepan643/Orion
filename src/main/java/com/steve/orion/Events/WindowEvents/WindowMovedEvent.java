package com.steve.orion.Events.WindowEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;
import com.steve.orion.Events.EventType;

public class WindowMovedEvent extends BaseEvent {
    private final int x, y;

    public int getX() { return x; }
    public int getY() { return y; }

    public WindowMovedEvent(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public EventType getEventType() {
        return EventType.WindowMoved;
    }

    @Override
    public String getName() {
        return "WindowMoved";
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.Application;
    }

    @Override
    public String toString() {
        return "WindowMovedEvent: " + getX() + ", " + getY();
    }
}
