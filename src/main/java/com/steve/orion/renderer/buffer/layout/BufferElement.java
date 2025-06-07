package com.steve.orion.renderer.buffer.layout;

import com.steve.orion.Log.Loggable;

import java.util.Objects;

public class BufferElement implements Loggable {
    private String name;
    private int offset;
    private int size;
    ShaderDataType type;
    private boolean normalized;

    public BufferElement(ShaderDataType type, String name) {
        this(type, name, false);
    }

    public BufferElement(ShaderDataType type, String name, boolean normalized) {
        this.name = name == null ? "" : name;
        if (type == ShaderDataType.None)
            CoreLog.warn("{}'s type is None", Objects.equals(name, "") ? "<Empty>" : name);
        this.type = type;
        this.offset = 0;
        this.normalized = normalized;
        this.size = ShaderDataType.shaderDataTypeSize(type);
    }


    public String getName() { return name; }
    public int getOffset() { return offset; }
    public int getSize() { return size; }
    public ShaderDataType getType() { return type; }
    public boolean isNormalized() { return normalized; }
    public int getComponentCount() {
        return switch (type) {
            case Float, Int, Bool ->    1;
            case Float2, Int2 ->        2;
            case Float3, Int3 ->        3;
            case Float4, Int4 ->        4;
            case Mat3 ->                3 * 3;
            case Mat4 ->                4 * 4;
            default -> {
                CoreLog.error("Unknown type");
                yield 0;
            }
        };
    }

    public void setOffset(int offset) { this.offset = offset; }
}
