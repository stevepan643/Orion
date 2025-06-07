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
import com.steve.orion.renderer.buffer.IndexBuffer;
import com.steve.orion.renderer.buffer.VertexBuffer;
import com.steve.orion.renderer.buffer.layout.BufferElement;
import com.steve.orion.renderer.buffer.layout.BufferLayout;
import com.steve.orion.renderer.buffer.layout.ShaderDataType;
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


    private VertexBuffer vertexBuffer;
    private IndexBuffer indexBuffer;

    int vertexArray;

    private int shaderTypeToOpenGLBaseType(ShaderDataType type) {
        return switch (type) {
            case Float,
                 Float2,
                 Float3,
                 Float4-> GL_FLOAT;
            case Mat3,
                 Mat4-> GL_FLOAT;
            case Int,
                 Int2,
                 Int3,
                 Int4-> GL_INT;
            case Bool -> GL_BOOL;
            default -> {
                CoreLog.error("Unknown type");
                yield 0;
            }
        };
    }

    public Application() {
        Window.WindowPros pros = new Window.WindowPros("Orion", 1280, 720);
        window = WindowsWindow.createWindow(pros);
        window.setCallback(this::onEvent);
        layerStack = new LayerStack();
        imGuiLayer = new ImGuiLayer(window);
        pushOverlay(imGuiLayer);

        vertexArray = glGenVertexArrays();
        glBindVertexArray(vertexArray);

        float[] vertices = {
                -0.5f, -0.5f, 0.0f, 0.8f, 0.2f, 0.8f, 1.0f,
                 0.5f, -0.5f, 0.0f, 0.2f, 0.3f, 0.8f, 1.0f,
                 0.0f,  0.5f, 0.0f, 0.8f, 0.8f, 0.2f, 1.0f,
        };

        vertexBuffer = VertexBuffer.create(vertices);

        {
            BufferLayout layout = new BufferLayout(
                    new BufferElement(ShaderDataType.Float3, "a_Position"),
                    new BufferElement(ShaderDataType.Float4, "a_Color")
            );

            vertexBuffer.setLayout(layout);
        }

        int index = 0;
        for (BufferElement element:vertexBuffer.getLayout().getElements()) {
            glEnableVertexAttribArray(index);
            glVertexAttribPointer(
                    index,
                    element.getComponentCount(),
                    shaderTypeToOpenGLBaseType(element.getType()),
                    element.isNormalized(),
                    vertexBuffer.getLayout().getStride(),
                    element.getOffset());
            index ++;
        }

        int[] indices = { 0, 1, 2 };
        indexBuffer = IndexBuffer.create(indices);

        shader = new Shader(
                Files.read("src/main/resources/v.vert"),
                Files.read("src/main/resources/f.frag"));
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
