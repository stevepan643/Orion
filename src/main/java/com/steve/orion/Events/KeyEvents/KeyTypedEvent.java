package com.steve.orion.Events.KeyEvents;

import com.steve.orion.Events.EventType;

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
