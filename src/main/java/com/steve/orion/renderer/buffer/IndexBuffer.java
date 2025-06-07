package com.steve.orion.renderer.buffer;

import com.steve.orion.Log.Loggable;
import com.steve.orion.platform.opengl.OpenGLIndexBuffer;
import com.steve.orion.renderer.RendererAPI;

public abstract class IndexBuffer implements Buffer, Loggable {
    protected int count;

    public int getCount() {
        return count;
    }

    public static IndexBuffer create(int[] indices) {
        switch (RendererAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLIndexBuffer(indices);
            }
        }
        CoreLog.error("Unknown API: {}", RendererAPI.getApi());
        return null;
    }
}
