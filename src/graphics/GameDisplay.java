package graphics;

import engine.GameEngine;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JFrame {

    GameEngine gameEngine;
    Board board;
    BoardG boardG;

    public GameDisplay(GameEngine gameEngine){

        this.gameEngine = gameEngine;

        this.add(new Buttons(gameEngine.gameController), BorderLayout.SOUTH);

        this.board = new Board(gameEngine.getTrain());
        this.add(this.board, BorderLayout.CENTER);

        this.boardG = new BoardG(gameEngine.getTrain());
        this.add(this.boardG, BorderLayout.NORTH);

        this.setTitle("Colt Express");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    public void update(){
        this.boardG.update();
        this.board.update();
    }
}
