package com.steve.orion.ImGui;

import com.steve.orion.Events.Event;
import com.steve.orion.Events.EventDispatcher;
import com.steve.orion.Events.EventType;
import com.steve.orion.Events.KeyEvents.KeyPressedEvent;
import com.steve.orion.Events.KeyEvents.KeyReleasedEvent;
import com.steve.orion.Events.KeyEvents.KeyTypedEvent;
import com.steve.orion.Events.MouseEvents.MouseButtonPressedEvent;
import com.steve.orion.Events.MouseEvents.MouseButtonReleasedEvent;
import com.steve.orion.Events.MouseEvents.MouseMovedEvent;
import com.steve.orion.Events.MouseEvents.MouseScrolledEvent;
import com.steve.orion.Events.WindowEvents.WindowResizeEvent;
import com.steve.orion.Layer.BaseLayer;
import com.steve.orion.Window;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiBackendFlags;
import imgui.flag.ImGuiKey;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.glViewport;

public class ImGuiLayer extends BaseLayer {
    private final ImGuiImplGl3 imGuiImplGl3 = new ImGuiImplGl3();
    private final ImGuiImplGlfw imGuiImplGlfw = new ImGuiImplGlfw();
    private final Window window;
    private double lastTime;

    public ImGuiLayer(Window window) {
        super("ImGui");
        this.window = window;
    }

    @Override
    public void onAttach() {
        ImGui.createContext();
        ImGui.styleColorsDark();
        ImGuiIO io= ImGui.getIO();

        double currentTime = glfwGetTime();
        io.setDeltaTime((float)(currentTime - lastTime));
        lastTime = currentTime;

        io.setDisplaySize(window.getWidth(), window.getHeight());

        io.addBackendFlags(ImGuiBackendFlags.HasMouseCursors);
        io.addBackendFlags(ImGuiBackendFlags.HasSetMousePos);

        io.setKeyMap(ImGuiKey.Tab, GLFW_KEY_TAB);
        io.setKeyMap(ImGuiKey.LeftArrow, GLFW_KEY_LEFT);
        io.setKeyMap(ImGuiKey.RightArrow, GLFW_KEY_RIGHT);
        io.setKeyMap(ImGuiKey.UpArrow, GLFW_KEY_UP);
        io.setKeyMap(ImGuiKey.DownArrow, GLFW_KEY_DOWN);
        io.setKeyMap(ImGuiKey.PageUp, GLFW_KEY_PAGE_UP);
        io.setKeyMap(ImGuiKey.PageDown, GLFW_KEY_PAGE_DOWN);
        io.setKeyMap(ImGuiKey.Home, GLFW_KEY_HOME);
        io.setKeyMap(ImGuiKey.End, GLFW_KEY_END);
        io.setKeyMap(ImGuiKey.Insert, GLFW_KEY_INSERT);
        io.setKeyMap(ImGuiKey.Delete, GLFW_KEY_DELETE);
        io.setKeyMap(ImGuiKey.Backspace, GLFW_KEY_BACKSPACE);
        io.setKeyMap(ImGuiKey.Space, GLFW_KEY_SPACE);
        io.setKeyMap(ImGuiKey.Enter, GLFW_KEY_ENTER);
        io.setKeyMap(ImGuiKey.Escape, GLFW_KEY_ESCAPE);
        io.setKeyMap(ImGuiKey.A, GLFW_KEY_A);
        io.setKeyMap(ImGuiKey.C, GLFW_KEY_C);
        io.setKeyMap(ImGuiKey.V, GLFW_KEY_V);
        io.setKeyMap(ImGuiKey.X, GLFW_KEY_X);
        io.setKeyMap(ImGuiKey.Y, GLFW_KEY_Y);
        io.setKeyMap(ImGuiKey.Z, GLFW_KEY_Z);

        imGuiImplGl3.init("#version 410");
        imGuiImplGlfw.init(window.getWindow(), false);
    }

    @Override
    public void onUpdate() {
//        imGuiImplGlfw.newFrame();
        ImGui.newFrame();

        // TODO
        ImGui.showDemoWindow();

        ImGui.render();
        imGuiImplGl3.renderDrawData(ImGui.getDrawData());
    }

    @Override
    public void onEvent(Event event) {
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(EventType.MouseButtonPressed, this::onMouseButtonPressedEvent);
        dispatcher.dispatch(EventType.MouseButtonReleased, this::onMouseButtonReleasedEvent);
        dispatcher.dispatch(EventType.MouseMoved, this::onMouseMovedEvent);
        dispatcher.dispatch(EventType.MouseScrolled, this::onMouseScrolledEvent);
        dispatcher.dispatch(EventType.KeyPressed, this::onKeyPressedEvent);
        dispatcher.dispatch(EventType.KeyReleased, this::onKeyReleasedEvent);
        dispatcher.dispatch(EventType.KeyTyped, this::onKeyTypedEvent);
        dispatcher.dispatch(EventType.WindowResize, this::onWindowResizedEvent);
    }

    @Override
    public void onDetach() {
        imGuiImplGl3.dispose();
        imGuiImplGlfw.dispose();
        ImGui.destroyContext();
    }



    public boolean onMouseButtonPressedEvent(MouseButtonPressedEvent event) {
        ImGuiIO io = ImGui.getIO();
        io.setMouseDown(event.getButton(), true);

        return false;
    }
    public boolean onMouseButtonReleasedEvent(MouseButtonReleasedEvent event) {
        ImGuiIO io = ImGui.getIO();
        io.setMouseDown(event.getButton(), false);

        return false;
    }
    public boolean onMouseMovedEvent(MouseMovedEvent event) {
        ImGuiIO io = ImGui.getIO();
        io.setMousePos(event.xPos(), event.yPos());
        return true;
    }
    public boolean onMouseScrolledEvent(MouseScrolledEvent event) {
        ImGuiIO io = ImGui.getIO();
        io.setMouseWheel(event.getYOffset());
        io.setMouseWheelH(event.getXOffset());

        return false;
    }
    public boolean onKeyPressedEvent(KeyPressedEvent event) {
        ImGuiIO io = ImGui.getIO();
        io.setKeysDown(event.getKeyCode(), true);

        io.setKeyCtrl(io.getKeysDown(GLFW_KEY_LEFT_CONTROL) || io.getKeysDown(GLFW_KEY_RIGHT_CONTROL));
        io.setKeyShift(io.getKeysDown(GLFW_KEY_LEFT_SHIFT) || io.getKeysDown(GLFW_KEY_RIGHT_SHIFT));
        io.setKeyAlt(io.getKeysDown(GLFW_KEY_LEFT_ALT) || io.getKeysDown(GLFW_KEY_RIGHT_ALT));
        io.setKeySuper(io.getKeysDown(GLFW_KEY_LEFT_SUPER) || io.getKeysDown(GLFW_KEY_RIGHT_SUPER));

        return false;
    }
    public boolean onKeyReleasedEvent(KeyReleasedEvent event) {
        ImGuiIO io = ImGui.getIO();
        io.setKeysDown(event.getKeyCode(), false);

        return false;
    }
    public boolean onKeyTypedEvent(KeyTypedEvent event) {
        ImGuiIO io = ImGui.getIO();
        int keyCode = event.getKeyCode();
        if (keyCode > 0 && keyCode < 0x10000)
            io.addInputCharacter(keyCode);

        return false;
    }
    public boolean onWindowResizedEvent(WindowResizeEvent event) {
        ImGuiIO io = ImGui.getIO();
        io.setDisplaySize(event.getWidth(), event.getHeight());
        io.setDisplayFramebufferScale(1.0f, 1.0f);
        glViewport(0, 0, window.getWidth(), window.getHeight());

        return false;
    }
}
