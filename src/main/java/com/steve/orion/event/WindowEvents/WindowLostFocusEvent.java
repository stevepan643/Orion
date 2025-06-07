package com.steve.orion.event.WindowEvents;

import com.steve.orion.event.BaseEvent;
import com.steve.orion.event.EventCategory;
import com.steve.orion.event.EventType;

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
