package com.steve.orion.renderer;

import com.steve.orion.Log.Loggable;
import com.steve.orion.platform.opengl.OpenGLShader;

public abstract class Shader implements Loggable {
    int renderID = 0;

    abstract public void cleanup();
    abstract public void bind();
    abstract public void unbind();

//    abstract public void uploadUniformFloat4(final String name, Vector4f vector4f);
//    abstract public void uploadUniformFloat4(final String name, final float x, float y, float z, float w);
//
//    abstract public void uploadUniform(final String name, final Matrix4f matrix4f);

    public static Shader create(String vertexSrc, String fragmentSrc) {
        switch (RendererAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLShader(vertexSrc, fragmentSrc);
            }
        }
        CoreLog.error("Unknown API: {}", RendererAPI.getApi());
        return null;
    }
}
