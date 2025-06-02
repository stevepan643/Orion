package com.steve.orion.Events.WindowEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;
import com.steve.orion.Events.EventType;

public class WindowLostFocusEvent extends BaseEvent {
    @Override
    public EventType getEventType() {
            return EventType.WindowLostFocus;
    }

    @Override
    public String getName() {
        return "WindowLostFocus";
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.Application;
    }
}
