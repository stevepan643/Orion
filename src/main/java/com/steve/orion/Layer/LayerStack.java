package com.steve.orion.layer;

import com.steve.orion.core.Timer;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.function.Consumer;

public class LayerStack {
    private final ArrayList<Layer> layers = new ArrayList<>();
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

    public interface LayerVisitor {
        boolean visit(Layer layer);
    }

    public interface ConsumerWithTimestep {
        void accept(Layer layer, Timer ts);
    }

    public void forEachLayer(Consumer<Layer> action) {
        layers.forEach(action);
    }

    public void forEachLayer(LayerVisitor visitor) {
        for (Layer layer : layers) {
            if (!visitor.visit(layer)) {
                break;
            }
        }
    }

    public void forEachLayerReverse(Consumer<Layer> action) {
        ListIterator<Layer> it = layers.listIterator(layers.size());
        while (it.hasPrevious()) {
            Layer layer = it.previous();
            action.accept(layer);
        }
    }

    public void forEachLayerReverse(LayerVisitor visitor) {
        ListIterator<Layer> it = layers.listIterator(layers.size());
        while (it.hasPrevious()) {
            if (!visitor.visit(it.previous())) {
                break;
            }
        }
    }

    public void forEachLayerReverse (ConsumerWithTimestep action, Timer ts) {
        ListIterator<Layer> it = layers.listIterator(layers.size());
        while (it.hasPrevious()) {
            Layer layer = it.previous();
            action.accept(layer, ts);
        }
    }
}
