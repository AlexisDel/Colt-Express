package main.graphics.graphicElements;

import main.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel {

    private final JButton up = new JButton("Up");
    private final JButton down = new JButton("Down");
    private final JButton left = new JButton("Left");
    private final JButton right = new JButton("Right");
    private final JButton shootUp = new JButton("ShootUp");
    private final JButton shootDown = new JButton("ShootDown");
    private final JButton shootRight = new JButton("ShootRight");
    private final JButton shootLeft = new JButton("ShootLeft");
    private final JButton rob = new JButton("Rob");
    private final JButton action = new JButton("Action !");

    public Buttons(GameController gameController) {

        // Adds ActionListener to the buttons
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

        // Configures the Buttons panel display
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        this.setPreferredSize(new Dimension(1000, 60));
        this.setLayout(new GridLayout(1, 10));


        // Adds the buttons to the panel
        this.add(up);
        this.add(down);
        this.add(left);
        this.add(right);
        this.add(shootUp);
        this.add(shootDown);
        this.add(shootLeft);
        this.add(shootRight);
        this.add(rob);
        this.add(action);
    }

    public void enableActionsButtons() {
        up.setEnabled(true);
        down.setEnabled(true);
        left.setEnabled(true);
        right.setEnabled(true);
        shootUp.setEnabled(true);
        shootDown.setEnabled(true);
        shootLeft.setEnabled(true);
        shootRight.setEnabled(true);
        rob.setEnabled(true);
    }

    public void enableActionButton() {
        action.setEnabled(true);
    }

    public void disableActionsButtons() {
        up.setEnabled(false);
        down.setEnabled(false);
        left.setEnabled(false);
        right.setEnabled(false);
        shootUp.setEnabled(false);
        shootDown.setEnabled(false);
        shootLeft.setEnabled(false);
        shootRight.setEnabled(false);
        rob.setEnabled(false);
    }

    public void disableActionButton() {
        action.setEnabled(false);
    }
}
