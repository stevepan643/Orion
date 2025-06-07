package com.steve.orion.event.KeyEvents;

import com.steve.orion.event.EventType;

public class KeyTypedEvent extends KeyEvent {
    public KeyTypedEvent(int keyCode) {
        super(keyCode);
    }

    @Override
    public EventType getEventType() {
        return EventType.KeyTyped;
    }

    @Override
    public String getName() {
        return "KeyTypedEvent";
    }

    @Override
    public String toString() {
        return "KeyTypedEvent: " + getKeyCode();
    }
}
