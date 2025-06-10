package com.steve.orion.layer;

import com.steve.orion.core.Timer;
import com.steve.orion.event.Event;

public abstract class BaseLayer implements Layer {
    protected String name;

    public BaseLayer(String name) {
        super();
        this.name = (name == null) ? "Layer" : name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void onAttach() {}
    @Override
    public void onDetach() {}
    @Override
    public void onUpdate(Timer timer) {}
    @Override
    public void onEvent(Event event) {}
    public void onImGuiRender() {}

}
