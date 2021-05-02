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

    Train train;

    private Image background;
    private Image trainImage;
    private Image banditImage;
    private Image marshalImage;
    private Image bountyImage;

    public BoardG(Train train) {

        this.train = train;

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.red);

        // Load images
        try {
            background = ImageIO.read(new File("ressources","background.png"));
            trainImage = ImageIO.read(new File("ressources","train.png"));
            banditImage = ImageIO.read(new File("ressources","characters.png")).getSubimage(49,0,39,47);
            marshalImage = ImageIO.read(new File("ressources","characters.png")).getSubimage(326,0,39,47);
            bountyImage = ImageIO.read(new File("ressources","bounty.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background, 0, 0, null);
        g2D.drawImage(trainImage, 138, 292, null);

        //TODO : use ID instead of instances (to get treasure and other bounty types)
        Image image = null;
        for (Entity e : train.getEntities()) {

            if (e instanceof Bandit) {
                image = banditImage;
            } else if (e instanceof Marshall) {
                image = marshalImage;
            } else if (e instanceof Bounty){
                image = bountyImage;
            } else {
                System.out.println(e.getClass());
            }
            g2D.drawImage(image, Positions.positions[e.getY()][e.getX()][0], Positions.positions[e.getY()][e.getX()][1], null);
        }
    }

    public void update(){
        this.repaint();
    }
}
