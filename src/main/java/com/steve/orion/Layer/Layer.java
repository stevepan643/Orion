package com.steve.orion.layer;

import com.steve.orion.core.Timer;
import com.steve.orion.event.Event;

public interface Layer {
    void onAttach();
    void onDetach();
    void onUpdate(Timer timestep);
    void onEvent(Event event);
    void onImGuiRender();

    String getName();
}
