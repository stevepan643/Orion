package com.steve.orion.Events;

public class EventCategory {
    public static int None = 0;
    public static int Application    = BIT(0);
    public static int Input          = BIT(1);
    public static int Keyboard       = BIT(2);
    public static int Mouse          = BIT(3);
    public static int MouseButton    = BIT(4);

    private static int BIT(int x) { return (1 << x); }

    private EventCategory() {}
}
