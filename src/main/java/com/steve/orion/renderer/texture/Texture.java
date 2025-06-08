package com.steve.orion.renderer.texture;

import com.steve.orion.core.Cleanable;

public interface Texture extends Cleanable {
    int getWidth();
    int getHeight();

    void bind(int slot);
}
