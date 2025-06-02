package com.steve.orion.Events;

public interface Event {
    EventType getEventType();
    String getName();
    int getCategoryFlags();
    boolean isInCategory(int category);
}
