package com.steve.orion.platform.Windows;

import com.steve.orion.Core;
import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.Callback;
import com.steve.orion.Events.KeyEvents.KeyPressedEvent;
import com.steve.orion.Events.KeyEvents.KeyReleasedEvent;
import com.steve.orion.Events.KeyEvents.KeyTypedEvent;
import com.steve.orion.Events.MouseEvents.MouseButtonPressedEvent;
import com.steve.orion.Events.MouseEvents.MouseButtonReleasedEvent;
import com.steve.orion.Events.MouseEvents.MouseMovedEvent;
import com.steve.orion.Events.MouseEvents.MouseScrolledEvent;
import com.steve.orion.Events.WindowEvents.WindowCloseEvent;
import com.steve.orion.Events.WindowEvents.WindowMovedEvent;
import com.steve.orion.Events.WindowEvents.WindowResizeEvent;
import com.steve.orion.Log.Log;
import com.steve.orion.Window;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class WindowsWindow extends Window {
    private WindowData data = new WindowData();
    private static boolean GLFWInitialized = false;

    public long getWindow() { return window; }

    private WindowsWindow(WindowPros pros) {
        init(pros);
    }

    private void init(WindowPros pros) {
        data.title = pros.Title();
        data.width = pros.width();
        data.height = pros.height();

        Log.CoreLog.info("Creating Window {} ({}x{})", data.title, data.width, data.height);

        if (!GLFWInitialized) {
            boolean success = glfwInit();
            Core.ASSERT(success, "Could not initialize GLFW!");

            GLFWInitialized = true;
        }

        window = glfwCreateWindow(pros.width(), pros.height(), data.title, NULL, NULL);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        setVSync(true);

        glfwSetWindowSizeCallback(window, (window, width, height) -> {
            WindowResizeEvent e = new WindowResizeEvent(width, height);
            data.width = width;
            data.height = height;
            eventCallback(e);
        });

        glfwSetWindowCloseCallback(window, (window) -> {
            WindowCloseEvent e = new WindowCloseEvent();
            eventCallback(e);
        });

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
           switch (action) {
               case GLFW_PRESS: {
                   KeyPressedEvent e = new KeyPressedEvent(key, 0);
                   eventCallback(e);
                   break;
               }
               case GLFW_RELEASE: {
                   KeyReleasedEvent e = new KeyReleasedEvent(key);
                   eventCallback(e);
                   break;
               }
               case GLFW_REPEAT: {
                   KeyPressedEvent e = new KeyPressedEvent(key, 1);
                   eventCallback(e);
                   break;
               }
           }
        });

        glfwSetCharCallback(window, (window, ch) -> {
            KeyTypedEvent e = new KeyTypedEvent(ch);
            eventCallback(e);
        });

        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            switch (action) {
                case GLFW_PRESS: {
                    MouseButtonPressedEvent e = new MouseButtonPressedEvent(button);
                    eventCallback(e);
                    break;
                }
                case GLFW_RELEASE: {
                    MouseButtonReleasedEvent e = new MouseButtonReleasedEvent(button);
                    eventCallback(e);
                    break;
                }
            }
        });

        glfwSetScrollCallback(window, (window, x, y) -> {
            MouseScrolledEvent e = new MouseScrolledEvent((float) x, (float) y);
            eventCallback(e);
        });

        glfwSetCursorPosCallback(window, (window, x, y) -> {
            MouseMovedEvent e = new MouseMovedEvent((float) x, (float) y);
            eventCallback(e);
        });

        glfwSetWindowPosCallback(window, (window, x, y) -> {
            WindowMovedEvent e = new WindowMovedEvent(x, y);
            eventCallback(e);
        });

        glfwSetErrorCallback(this::GLFWErrorCallback);

        new WindowsInput(window);
    }

    private void GLFWErrorCallback(int i, long l) {
        String message = MemoryUtil.memUTF8(l);
        System.err.println("GLFW Error " + i + ": " + message);
    }

    private void shutdown() {
        glfwDestroyWindow(window);
    }

    @Override
    public void onUpdate() {
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    @Override
    public int getWidth() {
        return data.width;
    }

    @Override
    public int getHeight() {
        return data.height;
    }

    @Override
    public void setVSync(boolean vsync) {
        if (vsync)
            glfwSwapInterval(1);
        else
            glfwSwapInterval(0);
        data.VSync = vsync;
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public boolean isVSync() {
        return data.VSync;
    }

    public static Window createWindow(WindowPros pros) {
        return new WindowsWindow(pros);
    }

    private void eventCallback(BaseEvent event) {
        callback.call(event);
    }

    @Override
    public void close(){
        shutdown();
    }
}

class WindowData {
    String title;
    int width;
    int height;

    boolean VSync;
}
