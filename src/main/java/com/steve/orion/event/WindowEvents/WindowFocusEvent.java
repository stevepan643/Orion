package com.steve.orion.event.WindowEvents;

import com.steve.orion.event.BaseEvent;
import com.steve.orion.event.EventCategory;
import com.steve.orion.event.EventType;

public class WindowFocusEvent extends BaseEvent {
    @Override
    public EventType getEventType() {
        return EventType.WindowFocus;
    }

    @Override
    public String getName() {
        return "WindowFocus";
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.Application;
    }
}
