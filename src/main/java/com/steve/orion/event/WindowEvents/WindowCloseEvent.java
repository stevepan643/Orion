package com.steve.orion.event.WindowEvents;

import com.steve.orion.event.BaseEvent;
import com.steve.orion.event.EventCategory;
import com.steve.orion.event.EventType;

public class WindowCloseEvent extends BaseEvent {
    @Override
    public EventType getEventType() {
        return EventType.WindowClose;
    }

    @Override
    public String getName() {
        return "WindowClose";
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.Application;
    }
}
