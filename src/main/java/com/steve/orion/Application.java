package com.steve.orion;

import com.steve.orion.core.Timestep;
import com.steve.orion.event.BaseEvent;
import com.steve.orion.event.EventDispatcher;
import com.steve.orion.event.EventType;
import com.steve.orion.event.WindowEvents.WindowCloseEvent;
import com.steve.orion.ImGui.ImGuiLayer;
import com.steve.orion.layer.Layer;
import com.steve.orion.layer.LayerStack;
import com.steve.orion.Log.Loggable;
import com.steve.orion.platform.Windows.WindowsWindow;

import java.io.Closeable;
import java.util.function.Consumer;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public abstract class Application implements Closeable, Loggable {
    protected Window window;
    private boolean running = true;
    private final LayerStack layerStack;
    private final ImGuiLayer imGuiLayer;
//    private Timestep timestep;
    private float lastFrameTime;

    public Application() {
        Window.WindowPros pros = new Window.WindowPros("Orion", 1280, 720);
        window = WindowsWindow.createWindow(pros);
        window.setCallback(this::onEvent);
        layerStack = new LayerStack();
        imGuiLayer = new ImGuiLayer(window);
        pushOverlay(imGuiLayer);
    }

    public void pushLayer(Layer layer) {
        layerStack.pushLayer(layer);
        layer.onAttach();
    }
    public void pushOverlay(Layer layer) {
        layerStack.pushOverlay(layer);
        layer.onAttach();
    }

    private void onEvent(BaseEvent event) {
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(EventType.WindowClose, this::onWindowClosed);

        layerStack.forEachLayerReverse(layer -> {
            layer.onEvent(event);
            return !event.isHandled();
        });
    }

    private boolean onWindowClosed(WindowCloseEvent event) {
        running = false;
        return true;
    }

    public void close() {
        window.close();
        layerStack.close();
    }

    public void run() {
        while (running) {
            float time = (float) glfwGetTime();
            Timestep timestep = new Timestep(time - lastFrameTime);
            lastFrameTime = time;

            layerStack.forEachLayerReverse(Layer::onUpdate, timestep);

            imGuiLayer.begin();
            layerStack.forEachLayerReverse(Layer::onImGuiRender);
            imGuiLayer.end();

            window.onUpdate();
        }
        CoreLog.info("Application stopped");
        CoreLog.info("Run: {}s", Core.getRuntime());
    }
}
