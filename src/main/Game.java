package main;

import main.engine.GameEngine;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public static int tickMarshall = 0;

    /**
     * Main game loop
     */
    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine();

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameEngine.isGameFinished) {

                    // Updates gameEngine every 20 ms
                    gameEngine.update();

                    // Moves the marshal every 50 seconds
                    tickMarshall++;
                    if (tickMarshall == 50) {
                        tickMarshall = 0;
                        gameEngine.getTrain().getMarshall().update();
                    }
                }
                else{
                    gameEngine.gameDisplay.boardG.endGameScreen();
                    gameEngine.gameDisplay.update();
                }
            }
        }, 0, 20);
    }
}
