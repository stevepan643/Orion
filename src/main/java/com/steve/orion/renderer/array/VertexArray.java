package com.steve.orion.renderer.array;

import com.steve.orion.Log.Loggable;
import com.steve.orion.platform.opengl.OpenGLVertexArray;
import com.steve.orion.renderer.RendererAPI;
import com.steve.orion.renderer.buffer.IndexBuffer;
import com.steve.orion.renderer.buffer.VertexBuffer;

public abstract class VertexArray implements Loggable {

    abstract public void bind();
    abstract public void unbind();

    abstract public VertexArray addVertexBuffer(VertexBuffer buffer);
    abstract public VertexArray setIndexBuffer(IndexBuffer buffer);
    abstract public IndexBuffer getIndexBuffer();
    abstract public VertexBuffer getVertexBuffer(int index);

    abstract public void cleanup();

    public static VertexArray create() {
        switch (RendererAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLVertexArray();
            }
        }
        CoreLog.error("Unknown API: {}", RendererAPI.getApi());
        return null;
    }
}
