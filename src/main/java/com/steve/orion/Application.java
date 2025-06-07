package com.steve.orion;

import com.steve.orion.Events.BaseEvent;
import com.steve.orion.Events.EventDispatcher;
import com.steve.orion.Events.EventType;
import com.steve.orion.Events.WindowEvents.WindowCloseEvent;
import com.steve.orion.ImGui.ImGuiLayer;
import com.steve.orion.Layer.Layer;
import com.steve.orion.Layer.LayerStack;
import com.steve.orion.Log.Loggable;
import com.steve.orion.io.Files;
import com.steve.orion.platform.Windows.WindowsWindow;
import com.steve.orion.renderer.Shader;
import org.lwjgl.opengl.GL11;

import java.io.Closeable;
import java.util.ListIterator;

import static org.lwjgl.opengl.GL30.*;

public abstract class Application implements Closeable, Loggable {
    protected Window window;
    private boolean running = true;
    private final LayerStack layerStack;
    private final ImGuiLayer imGuiLayer;
    private Shader shader;


    int vertexArray;

    public Application() {
        Window.WindowPros pros = new Window.WindowPros("Orion", 1280, 720);
        window = WindowsWindow.createWindow(pros);
        window.setCallback(this::onEvent);
        layerStack = new LayerStack();
        imGuiLayer = new ImGuiLayer(window);
        pushOverlay(imGuiLayer);

        vertexArray = glGenVertexArrays();
        glBindVertexArray(vertexArray);

        int vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);

        float[] vertices = {
                -0.5f, -0.5f, 0.0f,
                 0.5f, -0.5f, 0.0f,
                 0.0f,  0.5f, 0.0f,
        };

        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);

        int indexBuffer = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);

        int[] indices = { 0, 1, 2 };
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        shader = new Shader("src/main/resources/v.vert", "src/main/resources/f.frag");
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
        shader.cleanup();
        window.close();
        layerStack.close();
    }

    public void run() {
        while (running) {
            GL11.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            shader.bind();
            glBindVertexArray(vertexArray);
            glDrawElements(GL_TRIANGLES, 3, GL_UNSIGNED_INT, 0);

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
        CoreLog.info("Application stopped");
    }
}
