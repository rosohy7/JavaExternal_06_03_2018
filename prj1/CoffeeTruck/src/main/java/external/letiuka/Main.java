package external.letiuka;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            ConsoleUI ui = new ConsoleUI();
            ui.loop();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
