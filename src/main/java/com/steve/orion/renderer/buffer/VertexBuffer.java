package com.steve.orion.renderer.buffer;

import com.steve.orion.Log.Loggable;
import com.steve.orion.platform.opengl.OpenGLVertexBuffer;
import com.steve.orion.renderer.RendererAPI;
import com.steve.orion.renderer.buffer.layout.BufferLayout;

public abstract class VertexBuffer implements Buffer, Loggable {
    public static VertexBuffer create(float[] vertices) {
        switch (RendererAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLVertexBuffer(vertices);
            }
        }
        CoreLog.error("Unknown API: {}", RendererAPI.getApi());
        return null;
    }

    @Override
    abstract public VertexBuffer setLayout(BufferLayout layout);
}
