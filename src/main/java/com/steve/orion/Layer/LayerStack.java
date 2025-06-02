package com.steve.orion.Layer;

import java.util.ListIterator;
import java.util.Vector;

public class LayerStack {
    private final Vector<Layer> layers = new Vector<>();
    private int layerInsertIndex = 0;

    public void pushLayer(Layer layer) {
        layers.add(layerInsertIndex, layer);
        layerInsertIndex++;
    }

    public void pushOverlay(Layer overlay) {
        layers.add(overlay);
    }

    public void popLayer(Layer layer) {
        if (layers.remove(layer)) {
            layerInsertIndex--;
        }
    }

    public void popOverlay(Layer overlay) {
        layers.remove(overlay);
    }

    public ListIterator<Layer> begin() {
        return layers.listIterator(0);
    }

    public ListIterator<Layer> end() {
        return layers.listIterator(layers.size());
    }

    public void close() {
        for (Layer layer : layers) {
            layer.onDetach();
        }
    }
}
