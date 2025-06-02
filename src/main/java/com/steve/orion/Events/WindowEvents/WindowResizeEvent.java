package com.steve.orion.Events.WindowEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;
import com.steve.orion.Events.EventType;

public class WindowResizeEvent extends BaseEvent {
    private final int width, height;

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public WindowResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public EventType getEventType() {
        return EventType.WindowResize;
    }

    @Override
    public String getName() {
        return "WindowResize";
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.Application;
    }

    @Override
    public String toString() {
        return "WindowResizeEvent: " + width + "x" + height;
    }
}
