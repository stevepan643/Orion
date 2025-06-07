package com.steve.orion.renderer.buffer;

import com.steve.orion.Log.Loggable;
import com.steve.orion.platform.opengl.OpenGLIndexBuffer;
import com.steve.orion.renderer.RenderAPI;

public abstract class IndexBuffer implements Buffer, Loggable {
    public static IndexBuffer create(int[] indices) {
        switch (RenderAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLIndexBuffer(indices);
            }
        }
        CoreLog.error("Unknown API: {}", RenderAPI.getApi());
        return null;
    }
}
