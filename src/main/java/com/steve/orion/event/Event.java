package com.steve.orion.event;

public interface Event {
    EventType getEventType();
    String getName();
    int getCategoryFlags();
    boolean isInCategory(int category);
}
