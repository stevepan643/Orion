package com.steve.orion.event;

public class EventCategory {
    public static final int None = 0;
    public static final int Application    = BIT(0);
    public static final int Input          = BIT(1);
    public static final int Keyboard       = BIT(2);
    public static final int Mouse          = BIT(3);
    public static final int MouseButton    = BIT(4);

    private static int BIT(int x) { return (1 << x); }

    private EventCategory() {}
}
