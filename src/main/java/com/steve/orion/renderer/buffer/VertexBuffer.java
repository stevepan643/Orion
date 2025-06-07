package com.steve.orion.renderer.buffer;

import com.steve.orion.Log.Loggable;
import com.steve.orion.platform.opengl.OpenGLVertexBuffer;
import com.steve.orion.renderer.RenderAPI;

public abstract class VertexBuffer implements Buffer, Loggable {
    public static VertexBuffer create(float[] vertices) {
        switch (RenderAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLVertexBuffer(vertices);
            }
        }
        CoreLog.error("Unknown API: {}", RenderAPI.getApi());
        return null;
    }
}
