import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class TestLWJGL {
    public static void main(String[] args) {
        if (!GLFW.glfwInit()) {
            System.err.println("Failed to initialize GLFW");
            return;
        }

        long window = GLFW.glfwCreateWindow(800, 600, "Test LWJGL", 0, 0);
        if (window == 0L) {
            System.err.println("Failed to create the GLFW window");
            GLFW.glfwTerminate();
            return;
        }

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClearColor(0.2f, 0.3f, 0.8f, 1.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}