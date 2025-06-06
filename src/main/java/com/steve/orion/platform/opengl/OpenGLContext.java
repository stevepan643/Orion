package com.steve.orion.platform.opengl;

import com.steve.orion.Window;
import com.steve.orion.renderer.GraphicsContext;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

public class OpenGLContext implements GraphicsContext {
    private final Window window;

    public OpenGLContext(Window window) {
        this.window = window;
    }

    @Override
    public void init() {
        glfwMakeContextCurrent(window.getWindow());
        GL.createCapabilities();
    }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(window.getWindow());
    }
}
