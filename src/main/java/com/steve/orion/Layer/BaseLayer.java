package com.steve.orion.Layer;

import com.steve.orion.Events.Event;

public abstract class BaseLayer implements Layer {
    protected String name;

    public BaseLayer(String name) {
        super();
        if (name == null)
            name = "Layer";
        this.name = name;
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
    public void onUpdate() {}
    @Override
    public void onEvent(Event event) {}
    public void onImGuiRender() {}

}
