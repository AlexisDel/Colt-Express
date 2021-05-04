package graphics;

import engine.gameElements.Train;
import engine.gameElements.Entity;


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

        for (int i = 0; i < train.getTrainHeight(); i++){
            for (int j = 0; j < train.getTrainLength(); j++){
                this.display[i][j] = new JLabel("Empty");
                this.add(display[i][j]);
            }
        }
    }

    public void update(){
        //Clear all
        for (int i = 0; i < train.getTrainHeight(); i++) {
            for (int j = 0; j < train.getTrainLength(); j++) {
                display[i][j].setText("Empty");
            }
        }
        //Show
        for(Entity e: train.getEntities()){
            String text= display[e.getY()][e.getX()].getText();
            if(text!="Empty"){
                //display[e.getY()][e.getX()].setText(text+"\n"+e.getType());
            }

            else{
                //display[e.getY()][e.getX()].setText(e.getType());
            }
        }
    }


}
