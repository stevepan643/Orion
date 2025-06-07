package com.steve.orion.layer;

import com.steve.orion.core.Timestep;
import com.steve.orion.event.Event;

public interface Layer {
    void onAttach();
    void onDetach();
    void onUpdate(Timestep timestep);
    void onEvent(Event event);
    void onImGuiRender();

    String getName();
}
