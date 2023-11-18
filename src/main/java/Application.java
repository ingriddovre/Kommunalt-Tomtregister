/**
 * The main class of the application.
 * The class initiates the user interface in the main method.
 * Then calls the start method of the user interface, which starts the application with a few
 * properties in the register already.
 */
public class Application {
    /**
     * The main method of the application.
     * Initiates the user interface and calls the start method of the user interface.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}

// todo: error in 2 attributes PropertyRegister class