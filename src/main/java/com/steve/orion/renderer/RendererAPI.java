package com.steve.orion.renderer;

import com.steve.orion.renderer.array.VertexArray;
import org.joml.Vector4f;

public abstract class RendererAPI {
    public enum API{
        None,
        OpenGL;
    }

    private static RendererAPI.API api = API.None;

    public static void setApi(RendererAPI.API api) {
        RendererAPI.api = api;
    }

    public static RendererAPI.API getApi() {
        return api;
    }

    abstract public void init();

    abstract public void setClearColor(final Vector4f color);
    abstract public void clear();

    abstract public void drawIndexed(final VertexArray array);
}
