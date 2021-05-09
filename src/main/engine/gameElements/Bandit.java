package main.engine.gameElements;

import main.engine.utils.Action;
import main.engine.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bandit extends Character {
    private ArrayList<Bounty> bounties;
    private List<Action> actions;
    private int bullets;
    /**
     * returns Bandit object
     */
    public Bandit(String type, String name, Train train, int abs, int ord) {
        super(type, name, train, abs, ord);
        this.bounties = new ArrayList<>();
        this.actions = new ArrayList<>();
        this.bullets=5;

    }
    /**
     * Adds a bounty to the list of bounties
     */
    public void addBounty(Bounty b) {
        this.bounties.add(b);
    }

    /**
     * Returns the list of bounties
     * @return List<Bounty>
     */
    public List<Bounty> getBounty(){return this.bounties;}

    /**
     * Returns the sum of the value of all bounties in the list of bounties
     * @return int
     */
    public int getMoney() {
        int sum = 0;
        for (Bounty b : this.bounties) {
            sum += b.value;
        }
        return sum;
    }

    /**
     * Returns the attribute bullets
     * @return int
     */
    public int getBullets(){return this.bullets;}

    /**
     * Returns the list of actions
     * @return List<Action>
     */
    public List<Action> getActions() {
        return this.actions;
    }

    /**
     * Adds an action to the list of actions
     */
    public void addAction(Action a) {
        if (this.actions.size() < 2) this.actions.add(a);
    }

    /**
     * Clears the list of actions
     */
    public void clearActions(){this.actions.clear();}

    /**
     * Makes the bandit rob a random bounty from the train at his current position
     */
    public void rob() {
        //randomly pick one bounty from all available at bandit position
        if(this.train.getBountyAt(this.getX(),this.getY()).size()>0) {
            Random r = new Random();
            int randomBountyIndex = r.nextInt(this.train.getBountyAt(this.getX(),this.getY()).size());
            //rob it
            Bounty selectedBounty = this.train.getBountyAt(this.getX(),this.getY()).get(randomBountyIndex);
            //pick it from the train and puts it in his pocket
            this.addBounty(selectedBounty);
            this.train.removeEntity(selectedBounty);
            System.out.println(this.getName() + " has just robbed !");
        }
    }
    /**
     * Makes the bandit shoot in a specific direction
     * @param d a direction from the enumerate type Direction
     */
    public void shoot(Direction d) {
        //If the bandit has bullets allow him to shoot
        if(this.bullets>0) {
            //if bandit shoots, reduce his bullets by one
            this.bullets--;
            //Extract the bandits that are valid targets (All but himself)
            List<Bandit> targets = new ArrayList<>();
            for (Bandit b : this.train.getBandits()) {
                if (!b.equals(this)) {
                    targets.add(b);
                }
            }
            //Shoot in the desired direction
            switch (d) {
                case LEFT -> {
                    for (Bandit b : targets) {
                        if (this.y == b.getY() && this.x > b.getX()) {
                            //if a target is found make him drop his bounty
                            b.dropBounty();
                            System.out.println(this.getName() + " shot " + b.getName());
                        }
                    }
                }
                case RIGHT -> {
                    for (Bandit b : targets) {
                        if (this.y == b.getY() && this.x < b.getX()) {
                            //if a target is found make him drop his bounty
                            b.dropBounty();
                            System.out.println(this.getName() + " shot " + b.getName());
                        }
                    }
                }
                case UP -> {
                    for (Bandit b : targets) {
                        if ((this.x == b.getX() && this.y > b.getY()) || (this.x == b.getX() && this.y == b.getY() && this.y == 0)) {
                            //if a target is found make him drop his bounty
                            b.dropBounty();
                            System.out.println(this.getName() + " shot " + b.getName());
                        }
                    }
                }
                case DOWN -> {
                    for (Bandit b : targets) {
                        if ((this.x == b.getX() && this.y < b.getY()) || (this.x == b.getX() && this.y == b.getY() && this.y == 1)) {
                            //if a target is found make him drop his bounty
                            b.dropBounty();
                            System.out.println(this.getName() + " shot " + b.getName());
                        }
                    }
                }
            }
        }else{
            //If the bandit has no more bullets announce it:
            System.out.println(this.getName()+" ran out of bullets");
        }
    }
    /**
     * Makes the bandit drop a random bounty from his bounties back on the train
     */
    public void dropBounty() {
        //if bandit had bounties
        if (this.bounties.size() > 0) {
            //pick a random bounty
            Random r = new Random();
            int randomBountyIndex = r.nextInt(this.bounties.size());
            Bounty selectedBounty = this.bounties.get(randomBountyIndex);
            //remove the randomly picked bounty from his bounties
            this.bounties.remove(selectedBounty);
            //put the bounty back on the train on the bandit's position
            selectedBounty.moveTo(this.x, this.y);
            //Adds the bounty back again to the train entities
            this.train.addEntity(selectedBounty);
        }
    }
    /**
     * Executes an action (Move, Shoot, Rob) for the bandit
     * @param a action from the enumerate type Action
     */
    public void doAction(Action a) {

        switch (a) {
            case MOVE_UP -> {
                this.move(Direction.UP);
            }
            case MOVE_DOWN -> {
                this.move(Direction.DOWN);
            }
            case MOVE_RIGHT -> {
                this.move(Direction.RIGHT);
            }
            case MOVE_LEFT -> {
                this.move(Direction.LEFT);
            }
            case SHOOT_UP -> {
                this.shoot(Direction.UP);
            }
            case SHOOT_DOWN -> {
                this.shoot(Direction.DOWN);
            }
            case SHOOT_RIGHT -> {
                this.shoot(Direction.RIGHT);
            }
            case SHOOT_LEFT -> {
                this.shoot(Direction.LEFT);
            }
            case ROB -> {
                this.rob();
            }


        }
    }

    /**
     * Updates the bandit in the main game loop,
     * executing the bandits actions one every iteration of the loop
     * until all the actions in the bandit's actions list have been executed
     */
    public void update() {
        //if the list is not empty
        if (this.actions.size() > 0) {
            //do last action in queue
            doAction(this.actions.get(0));
            //remove last action in queue
            this.actions.remove(0);
        }
        //if the list is empty, all actions should have been executed
    }

}

