package com.steve.orion.Events.WindowEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;
import com.steve.orion.Events.EventType;

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
