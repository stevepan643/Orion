package com.steve.orion.event;

public abstract class BaseEvent implements Event{
    protected boolean handled = false;

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    @Override
    public boolean isInCategory(int category) {
        return (getCategoryFlags() & category) != 0;
    }

    @Override
    public String toString() {
        return getName();
    }

}
