package com.steve.orion;

import com.steve.orion.event.Callback;

public abstract class Window {
    public record WindowPros(String Title, int width, int height) {}

    protected Callback callback;
    protected long window;

    // std::function<void(Event&)>
    abstract public void onUpdate();
    abstract public int getWidth();
    abstract public int getHeight();
    abstract public long getWindow();

    abstract public void setVSync(boolean vsync);
    abstract public boolean isVSync();
    abstract public void close();

    abstract public void setCallback(Callback callback);
}