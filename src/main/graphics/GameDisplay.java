package main.graphics;

import main.engine.GameEngine;
import main.graphics.graphicElements.BoardG;
import main.graphics.graphicElements.Buttons;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JFrame {

    private GameEngine gameEngine;
    private BoardG boardG;
    public Buttons buttons;

    /**
     * Returns a GameDisplay object
     * Manages GUI
     *
     * @param gameEngine
     */
    public GameDisplay(GameEngine gameEngine) {

        this.gameEngine = gameEngine;

        // Creates the buttons and adds them to the window
        this.buttons = new Buttons(gameEngine.gameController);
        this.add(this.buttons, BorderLayout.SOUTH);

        // Creates the graphical interface and adds it to the window
        this.boardG = new BoardG(gameEngine.getTrain(), gameEngine);
        this.add(boardG, BorderLayout.NORTH);

        // Window settings
        this.setTitle("Colt Express");
        this.setIconImage(new ImageIcon("ressources/icon.jpg").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Updates graphical components that needs to
     */
    public void update() {
        this.boardG.update();
    }
}
