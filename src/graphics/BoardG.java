package graphics;

import engine.gameElements.*;
import graphics.utils.Positions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BoardG extends JPanel {

    final int PANEL_WIDTH =  1000;
    final int PANEL_HEIGHT = 500;

    int railwaysX = 0;
    int railwaysVelocity = 10;

    int backgroundX = 0;
    int backgroundVelocity = 5;

    Train train;

    private Image background;
    private Image railways;

    private Image trainImage;
    private Image bandit1Image;
    private Image bandit2Image;
    private Image marshalImage;
    private Image treasureImage;
    private Image jewelImage;
    private Image bountyImage;

    public BoardG(Train train) {

        this.train = train;

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.red);

        // Load images
        try {
            background = ImageIO.read(new File("ressources","background.png"));
            railways = ImageIO.read(new File("ressources","railways.png"));
            trainImage = ImageIO.read(new File("ressources","train.png"));
            bandit1Image = ImageIO.read(new File("ressources","characters.png")).getSubimage(49,0,39,47);
            bandit2Image = ImageIO.read(new File("ressources","characters.png")).getSubimage(188,0,39,47);
            marshalImage = ImageIO.read(new File("ressources","characters.png")).getSubimage(326,0,39,47);
            treasureImage = ImageIO.read(new File("ressources","bounty.png")).getSubimage(0,0,39,47);
            jewelImage = ImageIO.read(new File("ressources","bounty.png")).getSubimage(39,0,39,47);
            bountyImage = ImageIO.read(new File("ressources","bounty.png")).getSubimage(78,0,39,47);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawBackground(Graphics2D g2D){

        railwaysX -= railwaysVelocity;

        if (railwaysX < -1000){
            railwaysX = 0;
        }

        backgroundX -= backgroundVelocity;

        if (backgroundX < -1000) {
            backgroundX = 0;
        }

        g2D.drawImage(background, backgroundX, 0, null);
        g2D.drawImage(railways, railwaysX, 415, null);
        g2D.drawImage(trainImage, 138, 352, null);

    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        drawBackground(g2D);

        //TODO : use ID instead of instances (to get treasure and other bounty types)
        Image image = null;
        for (Entity e : train.getEntities()) {

            if (e instanceof Bandit) {
                if(e.getID()=="CrocoMechant")
                image = bandit1Image;
                else{image=bandit2Image;}
            } else if (e instanceof Marshall) {
                image = marshalImage;
            } else if (e instanceof Bounty){
                if(e.getID() == "Treasure"){
                    image = treasureImage;
                }
                else if (e.getID() == "Jewel"){
                    image = jewelImage;
                }
                else {
                    image = bountyImage;
                }
            }
            g2D.drawImage(image, Positions.positions[e.getY()][e.getX()][0], Positions.positions[e.getY()][e.getX()][1], null);
        }
    }

    public void update(){
        this.repaint();
    }
}
