package com.steve.orion.platform.opengl;

import com.steve.orion.renderer.Shader;
import com.steve.orion.util.MemManager;
import org.joml.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class OpenGLShader extends Shader {

    private final Map<String, Integer> uniformLocations = new HashMap<>();

    private int rendererID;

    public OpenGLShader(String vertexSrc, String fragmentSrc) {
        int isCompiled;

        int vertexShader = glCreateShader(GL_VERTEX_SHADER);

        glShaderSource(vertexShader, vertexSrc);
        glCompileShader(vertexShader);

        isCompiled = glGetShaderi(vertexShader, GL_COMPILE_STATUS);
        if (isCompiled == GL_FALSE) {
            String infoLog = glGetShaderInfoLog(vertexShader);
            CoreLog.error("Compile error in {} shader:",
                    "Vertex");
            for (String line : infoLog.split("\n")) {
                CoreLog.error("  {}", line.trim());
            }
            glDeleteShader(vertexShader);
            return;
        }

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentSrc);
        glCompileShader(fragmentShader);

        isCompiled = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);
        if (isCompiled == GL_FALSE) {
            String infoLog = glGetShaderInfoLog(fragmentShader);
            CoreLog.error("Compile error in {} shader:",
                    "Fragment");
            for (String line : infoLog.split("\n")) {
                CoreLog.error("  {}", line.trim());
            }
            glDeleteShader(vertexShader);
            glDeleteShader(fragmentShader);
            return;
        }

        rendererID = glCreateProgram();

        glAttachShader(rendererID, vertexShader);
        glAttachShader(rendererID, fragmentShader);

        glLinkProgram(rendererID);

        int isLinked = glGetProgrami(rendererID, GL_LINK_STATUS);
        if (isLinked == GL_FALSE) {
            String infoLog = glGetProgramInfoLog(rendererID);
            CoreLog.error("Shader program link error:");
            for (String line : infoLog.split("\n")) {
                CoreLog.error("  {}", line.trim());
            }
            glDeleteProgram(rendererID);
            return;
        }

        glDetachShader(rendererID, vertexShader);
        glDetachShader(rendererID, fragmentShader);
    }

    public void cleanup() {
        glDeleteProgram(rendererID);
    }

    public void bind() {
        glUseProgram(rendererID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    private int getUniformLocation(String name) {
        if (uniformLocations.containsKey(name)) {
            return uniformLocations.get(name);
        }
        int location = glGetUniformLocation(rendererID, name);
        uniformLocations.put(name, location);
        return location;
    }

//== Float =============================================================================================================
    public void uploadUniform(final String name, final float x) {
        int location = getUniformLocation(name);
        glUniform1f(location, x);
    }

    public void uploadUniform(final String name, final float x, float y) {
        int location = getUniformLocation(name);
        glUniform2f(location, x, y);
    }
    public void uploadUniform(final String name, Vector2f vector2f) {
        int location = getUniformLocation(name);
        glUniform2f(location, vector2f.x, vector2f.y);
    }

    public void uploadUniform(final String name, final float x, float y, float z) {
        int location = getUniformLocation(name);
        glUniform3f(location, x, y, z);
    }
    public void uploadUniform(final String name, Vector3f vector3f) {
        int location = getUniformLocation(name);
        glUniform3f(location, vector3f.x, vector3f.y, vector3f.z);
    }

    public void uploadUniform(final String name, final float x, float y, float z, float w) {
        int location = getUniformLocation(name);
        glUniform4f(location, x, y, z, w);
    }
    public void uploadUniform(final String name, Vector4f vector4f) {
        int location = getUniformLocation(name);
        glUniform4f(location, vector4f.x, vector4f.y, vector4f.z, vector4f.w);
    }

//== Int ===============================================================================================================
    public void uploadUniform(final String name, int x) {
        int location = getUniformLocation(name);
        glUniform1i(location, x);
    }

//== Matrix ============================================================================================================
    public void uploadUniform(final String name, final Matrix3f matrix3f) {
        int location = getUniformLocation(name);
        FloatBuffer buffer = MemManager.getMat3Buffer();
        matrix3f.get(buffer);
        glUniformMatrix3fv(location, false, buffer);
    }

    public void uploadUniform(final String name, final Matrix4f matrix4f) {
        int location = getUniformLocation(name);
        FloatBuffer buffer = MemManager.getMat4Buffer();
        matrix4f.get(buffer);
        glUniformMatrix4fv(location, false, buffer);
    }
}
