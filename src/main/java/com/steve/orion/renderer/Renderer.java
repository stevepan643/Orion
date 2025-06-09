package com.steve.orion.renderer;

import com.steve.orion.math.Matrices;
import com.steve.orion.platform.opengl.OpenGLShader;
import com.steve.orion.renderer.array.VertexArray;
import com.steve.orion.renderer.camera.OrthographicCamera;
import com.steve.orion.renderer.shader.Shader;
import org.joml.Matrix4f;

public class Renderer {

    private record SceneData(Matrix4f viewProjectionMatrix) {};

    static SceneData sceneData;

    public static void init() {
        RenderCommand.init();
    }

    public static void beginScene(OrthographicCamera camera) {
        sceneData = new SceneData(camera.getViewProjectionMatrix());
    }
    public static void endScene() {}

    public static void submit(final Shader shader, final VertexArray vertexArray) {
        submit(shader, vertexArray, Matrices.MATRIX4F_ID);
    }
    public static void submit(final Shader shader, final VertexArray array, final Matrix4f transform) {
        shader.bind();
        ((OpenGLShader) shader).uploadUniform("u_ViewProjection", sceneData.viewProjectionMatrix);
        ((OpenGLShader) shader).uploadUniform("u_Transform", transform);
        array.bind();
        RenderCommand.drawIndexed(array);
    }

    public static RendererAPI.API getRendererAPI() {
        return RendererAPI.getApi();
    }
}
