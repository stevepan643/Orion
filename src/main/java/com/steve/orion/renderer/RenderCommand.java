package com.steve.orion.renderer;

import com.steve.orion.platform.opengl.OpenGLRendererAPI;
import com.steve.orion.renderer.array.VertexArray;
import org.joml.Vector4f;

public class RenderCommand {
    private static final RendererAPI rendererAPI = createRendererAPI();

    private static RendererAPI createRendererAPI() {
        return new OpenGLRendererAPI(); // TODO: another API, need switch
    }

    public static void setClearColor(float r, float g, float b, float a) {
        rendererAPI.setClearColor(new Vector4f(r, g, b, a));
    }

    public static void clear() {
        rendererAPI.clear();
    }

    public static void drawIndexed(final VertexArray array) {
        rendererAPI.drawIndexed(array);
    }
}
