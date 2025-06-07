package com.steve.orion.renderer.buffer.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BufferLayout {
    private List<BufferElement> elements = new ArrayList<>();
    private int stride;

    public BufferLayout(BufferElement ... elements) {
        this.elements.addAll(Arrays.asList(elements));
        calculateOffsetAndStride();
    }

    private void calculateOffsetAndStride() {
        int offset = 0;
        stride = 0;
        for (BufferElement element : elements) {
            element.setOffset(offset);
            offset += element.getSize();
            stride += element.getSize();
        }
    }

    public int getStride() { return stride; }
    public List<BufferElement> getElements() {
        return elements;
    }
}
