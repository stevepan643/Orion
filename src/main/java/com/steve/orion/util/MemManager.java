package com.steve.orion.util;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MemManager {
    private static final FloatBuffer mat3Buffer = BufferUtils.createFloatBuffer(9);
    private static final FloatBuffer mat4Buffer = BufferUtils.createFloatBuffer(16);
    private static final FloatBuffer vec3Buffer = BufferUtils.createFloatBuffer(3);
    private static final FloatBuffer vec4Buffer = BufferUtils.createFloatBuffer(4);
    private static final IntBuffer int1Buffer = BufferUtils.createIntBuffer(1);

    public static FloatBuffer getMat3Buffer() {
        mat3Buffer.clear();
        return mat3Buffer;
    }

    public static FloatBuffer getMat4Buffer() {
        mat4Buffer.clear();
        return mat4Buffer;
    }

    public static FloatBuffer getVec3Buffer() {
        vec3Buffer.clear();
        return vec3Buffer;
    }

    public static FloatBuffer getVec4Buffer() {
        vec4Buffer.clear();
        return vec4Buffer;
    }

    public static IntBuffer getInt1Buffer() {
        int1Buffer.clear();
        return int1Buffer;
    }
}
