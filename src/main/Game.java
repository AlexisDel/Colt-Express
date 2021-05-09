package main;

import main.engine.GameEngine;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine();


        // Update gameEngine every 100 ms
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameEngine.isGameFinished){
                    gameEngine.update();
                }
            }
        }, 0, 20);

        Timer t2 = new Timer();
        t2.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameEngine.isGameFinished){
                    gameEngine.getTrain().getMarshall().update();
                }
            }
        }, 0, 1000);
    }
}