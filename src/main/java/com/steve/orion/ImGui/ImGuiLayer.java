package com.steve.orion.ImGui;

import com.steve.orion.Layer.BaseLayer;
import com.steve.orion.Window;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImGuiStyle;
import imgui.ImVec4;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImBoolean;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;

public class ImGuiLayer extends BaseLayer {
    private final ImGuiImplGl3 imGuiImplGl3 = new ImGuiImplGl3();
    private final ImGuiImplGlfw imGuiImplGlfw = new ImGuiImplGlfw();
    private final Window window;
    public static ImBoolean show = new ImBoolean(true);

    public ImGuiLayer(Window window) {
        super("ImGui");
        this.window = window;
    }

    @Override
    public void onAttach() {
//        ImGui.getVersion();
        ImGui.createContext();
        ImGuiIO io= ImGui.getIO();

        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
//        io.addConfigFlags(ImGuiConfigFlags.NavEnableGamepad);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);

        ImGui.styleColorsDark();

        ImGuiStyle style = ImGui.getStyle();
        if ((io.getConfigFlags() & ImGuiConfigFlags.ViewportsEnable) != 0) {
            style.setWindowRounding(0.0f);
            ImVec4 color = style.getColor(ImGuiCol.WindowBg);
            style.setColor(ImGuiCol.WindowBg, color.x, color.y, color.z, 1.0f);
        }

        imGuiImplGlfw.init(window.getWindow(), true);
        imGuiImplGl3.init("#version 410");
    }

    @Override
    public void onDetach() {
        imGuiImplGl3.dispose();
        imGuiImplGlfw.dispose();
        ImGui.destroyContext();
    }

    @Override
    public void onImGuiRender() {
        ImGui.showDemoWindow(show);
    }

    public void begin() {
        imGuiImplGlfw.newFrame();
        ImGui.newFrame();
    }

    public void end() {
        ImGuiIO io = ImGui.getIO();
        io.setDisplaySize(window.getWidth(), window.getHeight());

        ImGui.render();
        imGuiImplGl3.renderDrawData(ImGui.getDrawData());

        if ((io.getConfigFlags() & ImGuiConfigFlags.ViewportsEnable) != 0) {
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(window.getWindow());
        }
    }
}
