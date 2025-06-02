package com.steve.orion.Events.KeyEvents;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventCategory;

public abstract class KeyEvent extends BaseEvent {
    private final int keyCode;

    public KeyEvent(int keyCode) {
        super();
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.Keyboard | EventCategory.Input;
    }
}
