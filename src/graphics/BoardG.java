package graphics;

import engine.GameEngine;
import engine.gameElements.*;
import graphics.utils.Positions;
import graphics.utils.TextDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BoardG extends JPanel {

    private Train train;
    private GameEngine gameEngine;
    private boolean startScreen;

    // Positions & Sizes
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 500;
    private final int MAIN_HUD_X = PANEL_WIDTH / 2 - 50;
    private final int MAIN_HUD_WIDTH = 100;
    private final int MAIN_HUD_HEIGHT = 50;

    //Animations
    private int railwaysX = 0;
    private final int railwaysXVelocity = 10;

    private int backgroundX = 0;
    private final int backgroundXVelocity = 5;

    private int backgroundY = 0;
    private final int backgroundYVelocity = 15;

    // Images
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

    private Font font;

    public BoardG(Train train, GameEngine gameEngine) {

        this.train = train;
        this.gameEngine = gameEngine;
        this.startScreen = true;

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.red);

        // Loads images
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

        // Loads font
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("ressources", "The Bandido.otf"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Updates X position of railways and background to create infinite movement effect
     */
    private void updateXAnimation() {

        this.railwaysX -= this.railwaysXVelocity;
        if (this.railwaysX < -1000) {
            this.railwaysX = 0;
        }

        this.backgroundX -= this.backgroundXVelocity;
        if (this.backgroundX < -1000) {
            this.backgroundX = 0;
        }
    }

    /**
     * Updates Y position of background to create the starting animation
     */
    private void updateYAnimation() {

        this.backgroundY -= this.backgroundYVelocity;
        if (this.backgroundY < -500) {
            this.startScreen = false;
            this.backgroundY = -500;
        }
    }

    private void drawBackground(Graphics2D g2D) {

        g2D.drawImage(background, this.backgroundX, this.backgroundY, null);
        g2D.drawImage(railways, this.railwaysX, this.backgroundY, null);
        g2D.drawImage(trainImage, 0, this.backgroundY, null);
    }

    private void drawEntities(Graphics2D g2D) {

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

        // Main Hud settings
        g2D.setFont(this.font.deriveFont(Font.BOLD, 40));
        g2D.setPaint(Color.gray);

        // Main HUD
        TextDisplay.drawCenteredString(g2D, gameEngine.gameState.toString(), new Rectangle(MAIN_HUD_X, 25, MAIN_HUD_WIDTH, MAIN_HUD_HEIGHT));
        if (gameEngine.gameController.getPlayerTurnAsString() != null) {
            TextDisplay.drawCenteredString(g2D, gameEngine.gameController.getPlayerTurnAsString(), new Rectangle(MAIN_HUD_X, 75, MAIN_HUD_WIDTH, MAIN_HUD_HEIGHT));
            TextDisplay.drawCenteredString(g2D, gameEngine.gameController.getClickLeftPerPlayerAsString(), new Rectangle(MAIN_HUD_X, 125, MAIN_HUD_WIDTH, MAIN_HUD_HEIGHT));
        }

        // Players HUD settings
        g2D.setFont(new Font("Arial", Font.BOLD, 25));
        g2D.setPaint(Color.lightGray);

        // Player 1 HUD
        g2D.drawString("Player 1", 50, 50);
        g2D.drawString(String.valueOf(train.getBandits().get(0).getMoney()), 75, 80);
        g2D.drawImage(coinImage, 50, 63, null);
        for (int i = 0; i < train.getBandits().get(0).getBullets(); i++) {
            g2D.drawImage(gunImage, (50 + i * 20), 86, null);
        }

        // Player 2 HUD
        g2D.drawString(("Player 2"), 850, 50);
        g2D.drawString(String.valueOf(train.getBandits().get(1).getMoney()), 875, 80);
        g2D.drawImage(coinImage, 850, 63, null);
        for (int i = 0; i < train.getBandits().get(1).getBullets(); i++) {
            g2D.drawImage(gunImage, (850 + i * 20), 86, null);
        }
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        drawBackground(g2D);

        if (startScreen) {
            updateYAnimation();
        } else {
            updateXAnimation();
            drawEntities(g2D);
            drawHUD(g2D);
        }
    }

    public void update() {
        this.repaint();
    }
}
