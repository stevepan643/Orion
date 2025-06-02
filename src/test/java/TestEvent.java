import com.steve.orion.Events.EventCategory;
import com.steve.orion.Events.KeyEvents.KeyPressedEvent;
import com.steve.orion.Events.KeyEvents.KeyReleasedEvent;
import com.steve.orion.Events.WindowEvents.WindowResizeEvent;

public class TestEvent {
    public static void main(String[] args) {
        KeyPressedEvent e1 = new KeyPressedEvent('A', 0);
        System.out.println(e1);
        KeyReleasedEvent e2 = new KeyReleasedEvent('A');
        System.out.println(e2);
        WindowResizeEvent e3 = new WindowResizeEvent(1280, 720);
        if (e3.isInCategory(EventCategory.Application))
            System.out.println(e3);
    }
}
