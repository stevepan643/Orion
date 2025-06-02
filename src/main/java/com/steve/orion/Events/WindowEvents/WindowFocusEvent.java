package com.steve.orion.Events.WindowEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;
import com.steve.orion.Events.EventType;

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
