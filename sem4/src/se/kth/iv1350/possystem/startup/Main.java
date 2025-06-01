package startup;

import controller.Controller;
import view.View;


public class Main {
    /**
     * Starts the program by creating the controller and the view.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        new View(controller);
    }
}
