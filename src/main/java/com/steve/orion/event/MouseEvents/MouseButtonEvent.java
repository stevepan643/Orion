package com.steve.orion.event.MouseEvents;

import com.steve.orion.event.BaseEvent;
import com.steve.orion.event.EventCategory;

public abstract class MouseButtonEvent extends BaseEvent {
    protected final int button;

    public int getButton() { return button; }

    public MouseButtonEvent(int button) {
        super();
        this.button = button;
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.MouseButton | EventCategory.Input;
    }
}
