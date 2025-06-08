package com.steve.orion.renderer.texture;

import com.steve.orion.Log.Loggable;
import com.steve.orion.platform.opengl.OpenGLTexture;
import com.steve.orion.renderer.RendererAPI;

public abstract class Texture2D implements Texture, Loggable {
    public static Texture2D create(String path) {
        switch (RendererAPI.getApi()) {
            case None -> CoreLog.error("Need set RenderAPI");
            case OpenGL -> {
                return new OpenGLTexture(path);
            }
        }
        CoreLog.error("Unknown API: {}", RendererAPI.getApi());
        return null;
    }
}
