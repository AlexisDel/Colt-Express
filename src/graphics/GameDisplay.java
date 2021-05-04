package graphics;

import engine.GameEngine;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JFrame {

    GameEngine gameEngine;
    BoardG boardG;

    public GameDisplay(GameEngine gameEngine){

        this.gameEngine = gameEngine;

        this.add(new Buttons(gameEngine.gameController), BorderLayout.SOUTH);

        this.boardG = new BoardG(gameEngine.getTrain(), gameEngine);
        this.add(this.boardG, BorderLayout.NORTH);

        this.setTitle("Colt Express");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    public void update(){
        this.boardG.update();
    }
}
