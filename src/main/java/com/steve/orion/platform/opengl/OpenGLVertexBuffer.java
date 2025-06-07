package com.steve.orion.platform.opengl;

import com.steve.orion.renderer.buffer.VertexBuffer;
import com.steve.orion.renderer.buffer.layout.BufferLayout;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL45.glCreateBuffers;

public class OpenGLVertexBuffer extends VertexBuffer {
    private final int renderID;
    private BufferLayout bufferLayout;

    public OpenGLVertexBuffer(float[] vertices) {
        renderID = glCreateBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, renderID);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, renderID);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    @Override
    public void cleanup() {
        glDeleteBuffers(renderID);
    }

    @Override
    public void setLayout(BufferLayout layout) {
        this.bufferLayout = layout;
    }

    @Override
    public BufferLayout getLayout() {
        return bufferLayout;
    }
}
