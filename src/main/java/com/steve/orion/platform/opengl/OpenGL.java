package com.steve.orion.platform.opengl;

import com.steve.orion.Log.Loggable;
import com.steve.orion.renderer.buffer.layout.ShaderDataType;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_INT;
import static org.lwjgl.opengl.GL20.GL_BOOL;

public class OpenGL implements Loggable {
    public static int shaderTypeToOpenGLBaseType(ShaderDataType type) {
        return switch (type) {
            case Float,
                 Float2,
                 Float3,
                 Float4-> GL_FLOAT;
            case Mat3,
                 Mat4-> GL_FLOAT;
            case Int,
                 Int2,
                 Int3,
                 Int4-> GL_INT;
            case Bool -> GL_BOOL;
            default -> {
                CoreLog.error("Unknown type");
                yield 0;
            }
        };
    }
}
