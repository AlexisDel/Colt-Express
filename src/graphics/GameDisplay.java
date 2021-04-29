package graphics;

import engine.GameEngine;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JFrame {

    GameEngine gameEngine;
    Board board;

    public GameDisplay(GameEngine gameEngine){

        this.gameEngine = gameEngine;

        this.add(new Buttons(gameEngine.gameController), BorderLayout.SOUTH);

        this.board = new Board(gameEngine.train);
        this.add(this.board, BorderLayout.CENTER);

        this.setTitle("Colt Express");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void update(){
        this.board.update();
    }
}
