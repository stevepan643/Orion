package com.steve.orion.Events.KeyEvents;

import com.steve.orion.Events.EventType;

public class KeyReleasedEvent extends KeyEvent{
    public KeyReleasedEvent(int keyCode) {
        super(keyCode);
    }

    @Override
    public EventType getEventType() {
        return EventType.KeyReleased;
    }

    @Override
    public String getName() {
        return "KeyReleased";
    }

    @Override
    public String toString() {
        return "KeyReleasedEvent: " + getKeyCode();
    }
}
