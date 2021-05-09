package main.engine.gameElements;

import main.engine.utils.Direction;

import java.util.Random;

public class Marshall extends Character {
    private final float NERVOSITE_MARSHALL;

    /**
     * Returns a Marshall Object
     */
    public Marshall(Train train, int abs) {
        super("Marshall", train, abs, 1);
        this.NERVOSITE_MARSHALL = (float) 0.3;
    }

    /**
     * Automatically moves the Marshall in a random direction (left or right)
     */
    public void autoMove() {
        //Randomly decides if the marshall should move
        Random r = new Random();
        int probability = r.nextInt(100);
        boolean randomDirection = r.nextBoolean();
        //if the marshall should move then randomly decide to move it left or right
        if (probability <= NERVOSITE_MARSHALL * 100) {
            if (randomDirection) {
                this.move(Direction.LEFT);
            } else {
                this.move(Direction.RIGHT);
            }
        }
    }

    /**
     * Verifies if the Marshall can catch a Bandit at his current position
     */
    public void catchBandit() {
        //for each bandit in the train
        for (Bandit b : this.train.getBandits()) {
            //if the bandit is at the Marshall current position
            if (this.x == b.getX() && b.getY() == 1) {
                //catch him, makes him drop his bounty and moves him to the roof
                System.out.println("The Marshall caught " + b.getName());
                b.dropBounty();
                b.move(Direction.UP);
                b.clearActions();
            }
        }
    }

    /**
     * moves the marshall and verifies if it can catch a bandit at his new position
     */
    public void update() {
        autoMove();
        catchBandit();
    }
}