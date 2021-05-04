package engine.gameElements;

import engine.utils.Direction;

import java.util.Random;

public class Marshall extends Character {
    private float NERVOSITE_MARSHALL;

    public Marshall(Train train, int abs) {
        super("Marshall", train, abs, 1);
        this.NERVOSITE_MARSHALL = (float) 0.4;
    }

    public void autoMove() {
        Random r = new Random();
        int probability = r.nextInt(100);
        boolean randomDirection = r.nextBoolean();
        if (probability <= NERVOSITE_MARSHALL * 100) {
            if (randomDirection) {
                this.move(Direction.LEFT);
            } else {
                this.move(Direction.RIGHT);
            }
        }
    }

    public void catchBandit() {
        for (Bandit b : this.train.getBandits()) {
            if (this.x == b.getX() && b.getY() == 1) {
                System.out.println("The Marshall caught " + b.getName());
                b.dropBounty();
                b.move(Direction.UP);
                b.clearActions();
            }
        }
    }

    public void update(){
        autoMove();
        catchBandit();
    }
}