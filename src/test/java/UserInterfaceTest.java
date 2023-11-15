import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserInterfaceTest {
  @Test
    void testUserInterface() {
        UserInterface ui = new UserInterface();
        assertNotNull(ui);
    }
  @Test
    void testMenu() {
        UserInterface ui = new UserInterface();
        ui.menu();
        assertNotNull(ui);
    }
  @Test
    void testStart() {
        UserInterface ui = new UserInterface();
        ui.start();
        assertNotNull(ui);
    }

}