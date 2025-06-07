package com.steve.orion.platform.opengl;

import com.steve.orion.Log.Loggable;
import com.steve.orion.Window;
import com.steve.orion.renderer.GraphicsContext;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class OpenGLContext implements GraphicsContext, Loggable {
    private final Window window;

    public OpenGLContext(Window window) {
        this.window = window;
    }

    @Override
    public void init() {
        glfwMakeContextCurrent(window.getWindow());
        GL.createCapabilities();
        CoreLog.info("OpenGL Info:");
        CoreLog.info("  Vendor: {}", glGetString(GL_VENDOR));
        CoreLog.info("  Renderer: {}", glGetString(GL_RENDERER));
        CoreLog.info("  Version: {}", glGetString(GL_VERSION));
    }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(window.getWindow());
    }
}
