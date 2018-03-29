package external.letiuka;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Labirynth grid= new Labirynth();
        try {
            grid.readLabirynth("lab.txt");
            grid.createStory();
            grid.fillSolution();
            //grid.fillWalls();
            grid.printSolution();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
