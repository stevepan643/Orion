package com.steve.orion.platform.opengl;

import com.steve.orion.renderer.array.VertexArray;
import com.steve.orion.renderer.buffer.IndexBuffer;
import com.steve.orion.renderer.buffer.VertexBuffer;
import com.steve.orion.renderer.buffer.layout.BufferElement;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL45.glCreateVertexArrays;

public class OpenGLVertexArray extends VertexArray {
    private List<VertexBuffer> vertexBuffers = new ArrayList<>();
    private IndexBuffer indexBuffer;
    private int rendererID;

    public OpenGLVertexArray() {
        rendererID = glCreateVertexArrays();
    }

    @Override
    public void bind() {
        glBindVertexArray(rendererID);
    }

    @Override
    public void unbind() {
        glBindVertexArray(0);
    }

    @Override
    public VertexArray addVertexBuffer(VertexBuffer buffer) {
        glBindVertexArray(rendererID);
        buffer.bind();

        int index = 0;
        for (BufferElement element:buffer.getLayout().getElements()) {
            glEnableVertexAttribArray(index);
            glVertexAttribPointer(
                    index,
                    element.getComponentCount(),
                    OpenGL.shaderTypeToOpenGLBaseType(element.getType()),
                    element.isNormalized(),
                    buffer.getLayout().getStride(),
                    element.getOffset());
            index ++;
        }

        vertexBuffers.add(buffer);

        return this;
    }

    @Override
    public VertexArray setIndexBuffer(IndexBuffer buffer) {
        glBindVertexArray(rendererID);
        buffer.bind();

        this.indexBuffer = buffer;

        return this;
    }

    @Override
    public IndexBuffer getIndexBuffer() {
        return indexBuffer;
    }

    @Override
    public VertexBuffer getVertexBuffer(int index) {
        return vertexBuffers.get(index);
    }

    @Override
    public void cleanup() {
        glDeleteVertexArrays(rendererID);
    }
}
