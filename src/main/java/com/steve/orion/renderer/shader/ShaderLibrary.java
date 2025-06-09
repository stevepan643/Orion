package com.steve.orion.renderer.shader;

import com.steve.orion.Log.Loggable;

import java.util.HashMap;
import java.util.Map;

public class ShaderLibrary implements Loggable {
    private Map<String, Shader> shaders = new HashMap<>();

    public ShaderLibrary() {}

    public void add(final String name, final Shader shader) {
        if (shaders.containsKey(name)) { CoreLog.error("Shader already exists: {}", name); return; }
        shaders.put(name, shader);
    }

    public void add(final Shader shader) {
        String name = shader.getName();
        add(name, shader);
    }


    public Shader load(final String filepath) {
        Shader shader = Shader.create(filepath);
        add(shader);
        return shader;
    }

    public Shader load(final String name, final String vertexSrc, final String fragmentSrc) {
        Shader shader = Shader.create(name, vertexSrc, fragmentSrc);
        add(name, shader);
        return shader;
    }

    public Shader get(final String name) {
        return shaders.get(name);
    }
}
