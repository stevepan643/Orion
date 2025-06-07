package com.steve.orion.renderer;

import com.steve.orion.renderer.array.VertexArray;
import com.steve.orion.renderer.camera.OrthographicCamera;
import org.joml.Matrix4f;

public class Renderer {

    private record SceneData(Matrix4f viewProjectionMatrix) {};

    static SceneData sceneData;

    public static void beginScene(OrthographicCamera camera) {
        sceneData = new SceneData(camera.getViewProjectionMatrix());
    }
    public static void endScene() {}

    public static void submit(final Shader shader, final VertexArray array) {
        shader.bind();
        shader.uploadUniformMat4("u_ViewProjection", sceneData.viewProjectionMatrix);
        array.bind();
        RenderCommand.drawIndexed(array);
    }

    public static RendererAPI.API getRendererAPI() {
        return RendererAPI.getApi();
    }
}
