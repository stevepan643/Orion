package com.steve.orion;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventDispatcher;
import com.steve.orion.Events.EventType;
import com.steve.orion.Events.WindowEvents.WindowCloseEvent;
import com.steve.orion.ImGui.ImGuiLayer;
import com.steve.orion.Layer.Layer;
import com.steve.orion.Layer.LayerStack;
import com.steve.orion.Log.Log;
import com.steve.orion.platform.Windows.WindowsWindow;
import org.lwjgl.opengl.GL11;

import java.io.Closeable;
import java.util.ListIterator;

public abstract class Application implements Closeable {
    protected Window window;
    private boolean running = true;
    private final LayerStack layerStack;
    private final ImGuiLayer imGuiLayer;

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

        ListIterator<Layer> it = layerStack.end();
        while (it.hasPrevious()) {
            Layer layer = it.previous();
            layer.onEvent(event);
            if (event.isHandled()) break;
        }
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
            GL11.glClearColor(0.2f, 0.3f, 0.8f, 1.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            for (ListIterator<Layer> it = layerStack.begin(); it.hasNext(); ) {
                Layer layer = it.next();
                layer.onUpdate();
            }

//            Input.CurPos pos = Input.getCurPos();
//            Log.CoreLog.debug("{}, {}", pos.x(), pos.y());
            imGuiLayer.begin();
            for (ListIterator<Layer> it = layerStack.begin(); it.hasNext(); ) {
                Layer layer = it.next();
                layer.onImGuiRender();
            }
            imGuiLayer.end();

            window.onUpdate();
        }
        Log.CoreLog.info("Application stopped");
    }
}
