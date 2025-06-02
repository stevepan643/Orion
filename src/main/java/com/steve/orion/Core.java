package com.steve.orion;

public class Core {
    private Core() {}

    public static void ASSERT(boolean b, String s) {
        if (!b)
            throw new AssertionError(s);
    }
}
