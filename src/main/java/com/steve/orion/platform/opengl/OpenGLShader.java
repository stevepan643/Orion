package com.steve.orion.platform.opengl;

import com.steve.orion.io.Files;
import com.steve.orion.renderer.shader.Shader;
import com.steve.orion.util.MemManager;
import org.joml.*;

import java.lang.Math;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class OpenGLShader extends Shader {

    private final Map<String, Integer> uniformLocations = new HashMap<>();

    public OpenGLShader(final String name, final String vertexSrc, final String fragmentSrc) {
        Map<Integer, String> shaderSources = new HashMap<>();
        shaderSources.put(GL_VERTEX_SHADER, vertexSrc);
        shaderSources.put(GL_FRAGMENT_SHADER, fragmentSrc);
        openglCompile(shaderSources);
        this.name = name;
    }

    public OpenGLShader(final String filepath) {
        String source = Files.read(filepath);
        Map<Integer, String> shaderSources = preProcess(source);
        openglCompile(shaderSources);

        int lastSlash = Math.max(filepath.lastIndexOf('/'), filepath.lastIndexOf('\\'));
        lastSlash = lastSlash == -1 ? 0 : lastSlash + 1;
        int lastDot = filepath.lastIndexOf('.');
        int count = lastDot == -1 ? filepath.length() - lastSlash : lastDot - lastSlash;

        this.name = filepath.substring(lastSlash, lastSlash + count);
    }

    private Map<Integer, String> preProcess(String source) {
        Map<Integer, String> shaderSources = new HashMap<>();

        String[] lines = source.split("\\R");
        StringBuilder currentShader = new StringBuilder();
        Integer currentType = null;

        for (String line : lines) {
            if (line.startsWith("#type")) {
                if (currentType != null) {
                    shaderSources.put(currentType, currentShader.toString().trim());
                    currentShader.setLength(0);
                }

                String type = line.substring(5).trim();
                currentType = shaderTypeFromString(type);
            } else {
                if (currentType != null) {
                    currentShader.append(line).append('\n');
                }
            }
        }

        if (currentType != null && !currentShader.isEmpty()) {
            shaderSources.put(currentType, currentShader.toString().trim());
        }

        return shaderSources;
    }

    private int shaderTypeFromString(String type) {
        return switch (type) {
            case "vertex" -> GL_VERTEX_SHADER;
            case "fragment", "pixel" -> GL_FRAGMENT_SHADER;
            default -> throw new IllegalArgumentException("Unknown shader type: " + type);
        };
    }

    private void openglCompile(final Map<Integer, String> shaderSources) {
        int isCompiled;
        int program = glCreateProgram();
        List<Integer> glShaderIDs = new ArrayList<>(shaderSources.size());
        for (Map.Entry<Integer, String> entry : shaderSources.entrySet()) {
            int type = entry.getKey();
            String source = entry.getValue();

            int shader = glCreateShader(type);

            glShaderSource(shader, source);
            glCompileShader(shader);

            isCompiled = glGetShaderi(shader, GL_COMPILE_STATUS);
            if (isCompiled == GL_FALSE) {
                String infoLog = glGetShaderInfoLog(shader);
                CoreLog.error("Compile error in shader:");
                for (String line : infoLog.split("\n")) {
                    CoreLog.error("  {}", line.trim());
                }
                glDeleteShader(shader);
                for (int id : glShaderIDs)
                    glDeleteShader(id);
                glDeleteProgram(program);
                throw new RuntimeException("Shader compilation failed.");
            }
            glAttachShader(program, shader);
            glShaderIDs.add(shader);
        }

        glLinkProgram(program);

        int isLinked = glGetProgrami(program, GL_LINK_STATUS);
        if (isLinked == GL_FALSE) {
            String infoLog = glGetProgramInfoLog(program);
            CoreLog.error("Shader program link error:");
            for (String line : infoLog.split("\n")) {
                CoreLog.error("  {}", line.trim());
            }
            glDeleteProgram(program);
            for (int id : glShaderIDs)
                glDeleteShader(id);
            return;
        }

        for (int id : glShaderIDs)
            glDetachShader(program, id);

        this.rendererID = program;
    }

    @Override
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
