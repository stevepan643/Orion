package com.steve.orion.renderer;

import com.steve.orion.Log.Loggable;

import static org.lwjgl.opengl.GL20.*;

public class Shader implements Loggable {

    private int rendererID;

    public Shader(String vertexSrc, String fragmentSrc) {
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
}
