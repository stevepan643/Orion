import com.steve.orion.Application;
import com.steve.orion.EntryPoint;
import com.steve.orion.Events.Event;
import com.steve.orion.ImGui.ImGuiLayer;
import com.steve.orion.Inputs.Input;
import com.steve.orion.Layer.BaseLayer;
import com.steve.orion.Log.Log;

import static com.steve.orion.Inputs.KeyCodes.ORION_KEY_TAB;

class Sandbox extends Application {
    public Sandbox() {
        super();
        pushLayer(new ExampleLayer());
        pushLayer(new ImGuiLayer(window));
    }
}

class ExampleLayer extends BaseLayer {
    public ExampleLayer() {
        super("Example");
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (Input.isKeyPressed(ORION_KEY_TAB))
            Log.AppLog.debug("Tab is pressed");
    }

    @Override
    public void onEvent(Event event) {
//        Log.AppLog.info("{}", event);
    }
}

public class SandboxMain extends EntryPoint {
    static {
        EntryPoint.register(new SandboxMain());
    }

    @Override
    protected Application createApplication() {
        return new Sandbox();
    }
}