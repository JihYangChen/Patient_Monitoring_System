import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScreenTest {

    @Test
    void display() {
        MessageDisplayer screen = new Screen();
        screen.display("[500] Tony is in danger! Cause: sensor2 123.0");
    }
}