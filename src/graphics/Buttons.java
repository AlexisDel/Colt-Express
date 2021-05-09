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
