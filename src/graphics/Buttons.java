package graphics;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel {

    private JButton up = new JButton("Haut");
    private JButton down = new JButton("Bas");
    private JButton left = new JButton("Gauche");
    private JButton right = new JButton("Droite");
    private JButton shootUp = new JButton("Tire Haut");
    private JButton shootDown = new JButton("Tire Bas");
    private JButton shootRight = new JButton("Tire Droite");
    private JButton shootLeft = new JButton("Tire Gauche");
    private JButton rob = new JButton("Braque");
    private JButton action = new JButton("Action !");

    public Buttons(GameController gameController){

        // Setup controller.GameController ActionListener
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.goUp();
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.goDown();
            }
        });
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.goLeft();
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.goRight();
            }
        });
        shootUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.shootUp();
            }
        });
        shootDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.shootDown();
            }
        });
        shootLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.shootLeft();
            }
        });
        shootRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.shootRight();
            }
        });
        rob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.rob();
            }
        });
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.action();
            }
        });

        // Setup controller.GameController display
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        this.setLayout(new GridLayout(1,10));

        // Ads buttons
        this.add(up);
        this.add(down);
        this.add(left);
        this.add(right);
        this.add(shootUp);
        this.add(shootDown);
        this.add(shootRight);
        this.add(shootLeft);
        this.add(rob);
        this.add(action);
    }
}
