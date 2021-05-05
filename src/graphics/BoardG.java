package graphics;

import engine.GameEngine;
import engine.gameElements.*;
import graphics.utils.Positions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BoardG extends JPanel {

    private Train train;
    private GameEngine gameEngine;

    final int PANEL_WIDTH = 1000;
    final int PANEL_HEIGHT = 500;

    int railwaysX = 0;
    int railwaysVelocity = 10;

    int backgroundX = 0;
    int backgroundVelocity = 5;

    private Image background;
    private Image railways;

    private Image trainImage;
    private Image bandit1Image;
    private Image bandit2Image;
    private Image marshalImage;
    private Image treasureImage;
    private Image jewelImage;
    private Image bagImage;
    private Image coinImage;
    private Image gunImage;

    private JLabel gameStateLabel;
    private Font font;

    public BoardG(Train train, GameEngine gameEngine) {

        this.train = train;
        this.gameEngine = gameEngine;

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.red);

        // Load images
        try {
            background = ImageIO.read(new File("ressources", "background.png"));
            railways = ImageIO.read(new File("ressources", "railways.png"));
            trainImage = ImageIO.read(new File("ressources", "train.png"));
            bandit1Image = ImageIO.read(new File("ressources", "characters.png")).getSubimage(49, 0, 39, 47);
            bandit2Image = ImageIO.read(new File("ressources", "characters.png")).getSubimage(188, 0, 39, 47);
            marshalImage = ImageIO.read(new File("ressources", "characters.png")).getSubimage(326, 0, 39, 47);
            treasureImage = ImageIO.read(new File("ressources", "bounty.png")).getSubimage(0, 0, 39, 47);
            jewelImage = ImageIO.read(new File("ressources", "bounty.png")).getSubimage(39, 0, 39, 47);
            bagImage = ImageIO.read(new File("ressources", "bounty.png")).getSubimage(78, 0, 39, 47);
            coinImage = ImageIO.read(new File("ressources", "coin.png"));
            gunImage = ImageIO.read(new File("ressources", "gun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load font
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("ressources", "The Bandido.otf"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void drawBackground(Graphics2D g2D) {

        railwaysX -= railwaysVelocity;

        if (railwaysX < -1000) {
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

    private void drawEntities(Graphics2D g2D) {

        //TODO : use ID instead of instances (to get treasure and other bounty types)
        for (Entity e : train.getEntities()) {

            Image image = switch (e.getType()) {
                case "Bandit0" -> bandit1Image;
                case "Bandit1" -> bandit2Image;
                case "Marshall" -> marshalImage;
                case "Treasure" -> treasureImage;
                case "Jewel" -> jewelImage;
                case "Bag" -> bagImage;
                default -> null;
            };

            g2D.drawImage(image, Positions.positions[e.getY()][e.getX()][0], Positions.positions[e.getY()][e.getX()][1], null);
        }

    }

    private void drawHUD(Graphics2D g2D) {
        g2D.setFont(this.font.deriveFont(Font.BOLD, 40));
        g2D.setPaint(Color.gray);

        g2D.drawString(gameEngine.gameState.toString(), 425, 50);
        if (gameEngine.gameController.getPlayerTurnAsString() != null) {
            g2D.drawString(gameEngine.gameController.getPlayerTurnAsString(), 425, 100);
            g2D.drawString(gameEngine.gameController.getClickLeftPerPlayerAsString(), 425, 150);
        } else {
            g2D.drawString(gameEngine.gameController.getClickLeftPerPlayerAsString(), 425, 100);
        }

        g2D.setFont(new Font("Arial", Font.BOLD, 25));
        g2D.setPaint(Color.lightGray);

        // TODO : replace 5 by bandit.getBullets()

        g2D.drawString(("Player 1"), 50, 50);
        g2D.drawString(String.valueOf(train.getBandits().get(0).getMoney()), 75, 80);
        g2D.drawImage(coinImage, 50, 63, null);
        for (int i = 0; i<5; i++){
            g2D.drawImage(gunImage, (50 + i*20), 86, null);
        }

        g2D.drawString(("Player 2"), 850, 50);
        g2D.drawString(String.valueOf(train.getBandits().get(1).getMoney()), 875, 80);
        g2D.drawImage(coinImage, 850, 63, null);
        for (int i = 0; i<5; i++){
            g2D.drawImage(gunImage, (850 + i*20), 86, null);
        }




    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        drawBackground(g2D);
        drawEntities(g2D);
        drawHUD(g2D);


    }

    public void update() {
        this.repaint();
    }
}
