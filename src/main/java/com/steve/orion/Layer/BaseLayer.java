package com.steve.orion.Layer;

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
}
