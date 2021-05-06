package graphics;

import engine.GameEngine;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JFrame {

    GameEngine gameEngine;
    public Buttons buttons;
    BoardG boardG;

    public GameDisplay(GameEngine gameEngine) {

        this.gameEngine = gameEngine;

        this.buttons = new Buttons(gameEngine.gameController);
        this.add(this.buttons, BorderLayout.SOUTH);

        this.boardG = new BoardG(gameEngine.getTrain(), gameEngine);
        this.add(boardG, BorderLayout.CENTER);

        this.setTitle("Colt Express");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    public void update() {
        this.boardG.update();
    }
}
