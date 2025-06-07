package com.steve.orion;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Core {
    private Core() {}

    public static void ASSERT(boolean b, String s) {
        if (!b)
            throw new AssertionError(s);
    }

    public static double getRuntime() {
        return glfwGetTime(); // TODO: Need encapsulation
    }

}
