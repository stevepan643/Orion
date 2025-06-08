package com.steve.orion.util.pool;

import org.joml.Matrix4f;

public class MatricesPools {
    private static final ObjectPool<Matrix4f> matrix4fPool = new ObjectPool<>(Matrix4f::new);

    public static Matrix4f getMatrix4f() {
        return matrix4fPool.get().identity();
    }

    public static void releaseMatrix4f(Matrix4f matrix4f) {
        matrix4fPool.release(matrix4f);
    }
}
