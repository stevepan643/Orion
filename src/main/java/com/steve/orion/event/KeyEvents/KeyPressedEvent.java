package com.steve.orion.event.KeyEvents;

import com.steve.orion.event.EventType;

public class KeyPressedEvent extends KeyEvent {
    private final int repeatCount;

    public KeyPressedEvent(int keyCode, int repeatCount) {
        super(keyCode);
        this.repeatCount = repeatCount;
    }

    @Override
    public EventType getEventType() {
        return EventType.KeyPressed;
    }

    @Override
    public String getName() {
        return "KeyPressed";
    }

    @Override
    public String toString() {
        return "KeyPressedEvent: " + getKeyCode() + " (" + repeatCount + " repeats)";
    }
}
