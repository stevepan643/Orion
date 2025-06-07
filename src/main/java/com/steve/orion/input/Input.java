package com.steve.orion.input;

public abstract class Input {
    private static Input instance;

    public static void setInstance(Input inputInstance) {
        instance = inputInstance;
    }

    public static boolean isKeyPressed(int key) {
        return instance.isKeyPressedImpl(key);
    }

    public static boolean isMouseButtonPressed(int button) {
        return instance.isMouseButtonPressedImpl(button);
    }

    public static float getMouseX() {
        return instance.getMouseXImpl();
    }

    public static float getMouseY() {
        return instance.getMouseYImpl();
    }

    public static CurPos getCurPos() {
        return instance.getCurPosImpl();
    }

    public record CurPos(float x, float y) {}

    abstract protected boolean isKeyPressedImpl(int keyCode);
    abstract protected boolean isMouseButtonPressedImpl(int button);
    abstract protected float getMouseXImpl();
    abstract protected float getMouseYImpl();
    abstract protected CurPos getCurPosImpl();
}
