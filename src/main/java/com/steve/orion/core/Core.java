package com.steve.orion.core;

import com.steve.orion.Log.Loggable;

import java.net.URL;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Core implements Loggable {
    private Core() {}

    public static void ASSERT(boolean b, String s) {
        if (!b)
            throw new AssertionError(s);
    }

    public static double getRuntime() {
        return glfwGetTime(); // TODO: Need encapsulation
    }

//    public static void checkNull(Object o, String msg) {
//        if (o == null) {
//            CoreLog.error(msg);
//            throw new NullPointerException(msg);
//        }
//    }

    public static <T> T checkNull(T o, String msg) {
        if (o == null) {
            CoreLog.error("[Null Check Failed] {}", msg);
            throw new NullPointerException(msg);
        }
        return o;
    }

    // FIXME:
    public static String getResourcePath(String relativePath) {
        URL url = Core.class.getClassLoader().getResource(relativePath);
        if (url == null) {
            CoreLog.error("Resource not found: {}", relativePath);
            return null;
        }
        return url.getPath();
    }
}
