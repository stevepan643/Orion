package com.steve.orion.platform.Windows;

import com.steve.orion.Inputs.Input;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class WindowsInput extends Input {
    private final long window;

    private final DoubleBuffer xPos = BufferUtils.createDoubleBuffer(1);
    private final DoubleBuffer yPos = BufferUtils.createDoubleBuffer(1);

    public WindowsInput(long window) {
        this.window = window;
        setInstance(this);
    }

    @Override
    protected boolean isKeyPressedImpl(int keyCode) {
        int state = glfwGetKey(window, keyCode);
        return state == GLFW.GLFW_PRESS || state == GLFW_REPEAT;
    }

    @Override
    protected boolean isMouseButtonPressedImpl(int button) {
        int state = glfwGetMouseButton(window, button);
        return state == GLFW.GLFW_PRESS;
    }

    @Override
    protected float getMouseXImpl() {
        GLFW.glfwGetCursorPos(window, xPos, yPos);
        return (float) xPos.get(0);
    }

    @Override
    protected float getMouseYImpl() {
        GLFW.glfwGetCursorPos(window, xPos, yPos);
        return (float) yPos.get(0);
    }

    @Override
    protected CurPos getCurPosImpl() {
        GLFW.glfwGetCursorPos(window, xPos, yPos);
        return new CurPos((float) xPos.get(0), (float) yPos.get(0));
    }
}
