package graphics;

import engine.GameEngine;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JFrame {

    GameEngine gameEngine;

    public GameDisplay(GameEngine gameEngine){

        this.gameEngine = gameEngine;

        // Ads buttons
        this.add(new Buttons(gameEngine.gameController), BorderLayout.SOUTH);

        this.setTitle("Colt Express");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }
}
