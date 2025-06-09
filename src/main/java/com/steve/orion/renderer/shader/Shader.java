package com.steve.orion.renderer.shader;

import com.steve.orion.Log.Loggable;
import com.steve.orion.core.Cleanable;
import com.steve.orion.platform.opengl.OpenGLShader;
import com.steve.orion.renderer.RendererAPI;

public abstract class Shader implements Loggable, Cleanable {
    protected int rendererID = 0;
    protected String name;

    abstract public void bind();
    abstract public void unbind();

    public String getName() {
        return name;
    }

    public static Shader create(final String name, final String vertexSrc, final String fragmentSrc) {
        switch (RendererAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLShader(name, vertexSrc, fragmentSrc);
            }
        }
        CoreLog.error("Unknown API: {}", RendererAPI.getApi());
        return null;
    }

    public static Shader create(final String filepath) {
        switch (RendererAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLShader(filepath);
            }
        }
        CoreLog.error("Unknown API: {}", RendererAPI.getApi());
        return null;
    }
}
