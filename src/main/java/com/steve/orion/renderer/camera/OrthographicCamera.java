package com.steve.orion.renderer.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class OrthographicCamera {
    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private Matrix4f viewProjectionMatrix;

    private Vector3f position;
    private float rotation;

    public OrthographicCamera(float left, float right, float bottom, float top) {
        projectionMatrix = new Matrix4f();
        projectionMatrix.ortho(left, right, bottom, top, -1, 1);
        viewMatrix = new Matrix4f();
        viewProjectionMatrix = new Matrix4f(viewMatrix).mul(projectionMatrix);
        position = new Vector3f();
        recalculateViewMatrix();
    }

    private void recalculateViewMatrix() {
        Matrix4f transform = new Matrix4f()
                .translation(position)
                .rotate((float) Math.toRadians(rotation), 0, 0, 1);
        viewMatrix.set(transform).invert();
        viewProjectionMatrix.set(projectionMatrix).mul(viewMatrix);
    }

    public void setPosition(Vector3f position) { this.position = position; recalculateViewMatrix(); }
    public void setPosition(float x, float y, float z) { this.position.set(x, y, z); recalculateViewMatrix(); }
    public void setRotation(float rotation) { this.rotation = rotation; recalculateViewMatrix(); }

    public Vector3f getPosition() { return position; }
    public float getRotation() { return rotation; }

    public void movePosition(float x, float y, float z) {
        this.position.x += x;
        this.position.y += y;
        this.position.z += z;
        recalculateViewMatrix();
    }
    public void movePositionX(float x) { this.position.x += x; recalculateViewMatrix(); }
    public void movePositionY(float y) { this.position.y += y; recalculateViewMatrix(); }
    public void movePositionZ(float z) { this.position.z += z; recalculateViewMatrix(); }

    public void rotate(float angle) { this.rotation += angle; recalculateViewMatrix(); }

    public Matrix4f getProjectionMatrix() { return projectionMatrix; }
    public Matrix4f getViewMatrix() { return viewMatrix; }
    public Matrix4f getViewProjectionMatrix() { return viewProjectionMatrix; }
}
