package com.steve.orion.Events.MouseEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;

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
