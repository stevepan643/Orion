package com.steve.orion.renderer;

public enum RenderAPI {
    None,
    OpenGL;

    private static RenderAPI api = None;

    public static void setApi(RenderAPI api) {
        RenderAPI.api = api;
    }

    public static RenderAPI getApi() {
        return api;
    }
}
