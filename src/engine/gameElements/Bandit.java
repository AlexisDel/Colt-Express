package engine.gameElements;

import engine.utils.Action;
import engine.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bandit extends Character {
    private ArrayList<Bounty> bounties;
    private List<Action> actions;
    private int bullets;

    public Bandit(String type, String name, Train train, int abs, int ord) {
        super(type, name, train, abs, ord);
        this.bounties = new ArrayList<>();
        this.actions = new ArrayList<>();
        this.bullets=5;

    }

    public void addBounty(Bounty b) {
        this.bounties.add(b);
    }

    public List<Bounty> getBounty(){return this.bounties;}

    public int getMoney() {
        int sum = 0;
        for (Bounty b : this.bounties) {
            sum += b.value;
        }
        return sum;
    }

    public int getBullets(){return this.bullets;}

    public List<Action> getActions() {
        return this.actions;
    }

    public void addAction(Action a) {
        if (this.actions.size() < 2) this.actions.add(a);
    }

    public void clearActions(){this.actions.clear();}

    public void rob() {
        //randomly pick one
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

    public void shoot(Direction d) {
        if(this.bullets>0) {
            //if bandit shoots, reduce his bullets by one
            this.bullets--;
            //Extract the bandits that are valid targets
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
                            b.dropBounty();
                            System.out.println(this.getName() + " shot " + b.getName());
                        }
                    }
                }
                case RIGHT -> {
                    for (Bandit b : targets) {
                        if (this.y == b.getY() && this.x < b.getX()) {
                            b.dropBounty();
                            System.out.println(this.getName() + " shot " + b.getName());
                        }
                    }
                }
                case UP -> {
                    for (Bandit b : targets) {
                        if ((this.x == b.getX() && this.y > b.getY()) || (this.x == b.getX() && this.y == b.getY() && this.y == 0)) {
                            b.dropBounty();
                            System.out.println(this.getName() + " shot " + b.getName());
                        }
                    }
                }
                case DOWN -> {
                    for (Bandit b : targets) {
                        if ((this.x == b.getX() && this.y < b.getY()) || (this.x == b.getX() && this.y == b.getY() && this.y == 1)) {
                            b.dropBounty();
                            System.out.println(this.getName() + " shot " + b.getName());
                        }
                    }
                }
            }
        }else{
            System.out.println(this.getName()+" ran out of bullets");
        }
    }

    public void dropBounty() {
        if (this.bounties.size() > 0) {
            Random r = new Random();
            int randomBountyIndex = r.nextInt(this.bounties.size());
            Bounty selectedBounty = this.bounties.get(randomBountyIndex);
            this.bounties.remove(selectedBounty);
            selectedBounty.moveTo(this.x, this.y);
            this.train.addEntity(selectedBounty);
        }
    }

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

    //TODO: throw exception if any actions are null
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

