package graphics;

import engine.gameElements.Train;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    Train train;
    JLabel[][] display;

    public Board(Train train) {

        this.train = train;
        this.display = new JLabel[2][4];

        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        this.setLayout(new GridLayout(train.getTrainHeight(),train.getTrainLength()));

        for (int i = 0; i < train.board.length; i++){
            for (int j = 0; j < train.board[i].length; j++){
                this.display[i][j] = new JLabel("Empty");
                this.add(display[i][j]);
            }
        }
    }

    public void update(){
        for (int i = 0; i < train.board.length; i++) {
            for (int j = 0; j < train.board[i].length; j++) {
                if (train.board[i][j] == null){
                    display[i][j].setText("Empty");
                } else {
                    display[i][j].setText(train.board[i][j].toString());
                }
            }
        }
    }
}
