package com.steve.orion.Layer;

import com.steve.orion.Events.Event;

public interface Layer {
    void onAttach();
    void onDetach();
    void onUpdate();
    void onEvent(Event event);

    String getName();
}
