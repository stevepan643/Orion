package com.steve.orion.renderer.buffer;

import com.steve.orion.renderer.buffer.layout.BufferLayout;

public interface Buffer {
    void bind();
    void unbind();
    void cleanup();
    void setLayout(BufferLayout layout);
    BufferLayout getLayout();
}
