package com.steve.orion.platform.opengl;

import com.steve.orion.renderer.RendererAPI;
import com.steve.orion.renderer.array.VertexArray;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLRendererAPI extends RendererAPI {
    @Override
    public void setClearColor(Vector4f color) {
        glClearColor(color.x, color.y, color.z, color.w);
    }

    @Override
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void drawIndexed(final VertexArray array) {
        glDrawElements(GL_TRIANGLES, array.getIndexBuffer().getCount(), GL_UNSIGNED_INT, 0);
    }
}
