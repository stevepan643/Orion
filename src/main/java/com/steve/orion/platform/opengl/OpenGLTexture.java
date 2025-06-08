package com.steve.orion.platform.opengl;

import com.steve.orion.Log.Loggable;
import com.steve.orion.core.Core;
import com.steve.orion.renderer.texture.Texture2D;

import java.nio.ByteBuffer;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.GL_RGB8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.stb.STBImage.*;

public class OpenGLTexture extends Texture2D implements Loggable {
    private final String path;
    private int width, height;
    private int renderID;

    public OpenGLTexture(String path) {
        this.path = path;

        int[]   width = new int[1],
                height = new int[1],
                channels = new int[1];

        nstbi_set_flip_vertically_on_load(1);
        ByteBuffer data =
                Objects.requireNonNull(
                        stbi_load(path, width, height, channels, 0),
                        String.format("Failed to load texture: %s", path)
                );

        this.width = width[0];
        this.height = height[0];

        int internalFormat = 0, dataFormat = 0;
        if (channels[0] == 4) {
            internalFormat = GL_RGBA8;
            dataFormat = GL_RGBA;
        } else if (channels[0] == 3) {
            internalFormat = GL_RGB8;
            dataFormat = GL_RGB;
        }

        renderID = glCreateTextures(GL_TEXTURE_2D);
        glTextureStorage2D(renderID, 1, internalFormat, this.width, this.height);

        glTextureParameteri(renderID, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTextureParameteri(renderID, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTextureSubImage2D(renderID, 0, 0, 0, this.width, this.height, dataFormat, GL_UNSIGNED_BYTE, data);

        stbi_image_free(data);
    }

    @Override
    public int getWidth() { return width; }
    @Override
    public int getHeight() { return height; }
    private String getPath() { return path; }

    @Override
    public void bind(int slot) {
        glBindTextureUnit(slot, renderID);
    }

    @Override
    public void cleanup() {
        glDeleteTextures(renderID);
    }
}
