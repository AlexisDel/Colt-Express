package engine.gameElements;

import engine.utils.Action;
import engine.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bandit extends Character {
    private ArrayList<Bounty> bounties;
    private List<Action> actions;

    public Bandit(String type, String name, Train train, int abs, int ord) {
        super(type, name, train, abs, ord);
        this.bounties = new ArrayList<>();
        this.actions = new ArrayList<>();

    }

    public void addBounty(Bounty b) {
        this.bounties.add(b);
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void addAction(Action a) {
        if (this.actions.size() < 2) this.actions.add(a);
    }

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
        switch (d) {
            case LEFT -> {
                //axis= x (true)
                shootTargets(this.x, 0, true);

                System.out.println(this.getName() + " shot left");
            }
            case RIGHT -> {
                //axis= x (true)
                shootTargets(this.x, this.train.getTrainLength() - 1, true);

                System.out.println(this.getName() + " shot right");
            }
            case UP -> {
                //axis= y (false)
                shootTargets(this.y, 0, false);

                System.out.println(this.getName() + " shot up");
            }
            case DOWN -> {
                //axis= y (false)
                shootTargets(this.y, 1, false);

                System.out.println(this.getName() + " shot down");
            }
        }
    }

    public void shootTargets(int from, int to, boolean axis) {
        String targets = "";
        for (int position = from; position <= to; position++) {

            for (Bandit b : this.train.getBandits()) {
                //disable shoot yourself
                if (!b.equals(this)) {
                    //shoot left or right
                    if (axis) {
                        if (b.getX() == position && b.getY() == this.y) {
                            targets = targets + b.getName();
                            b.dropBounty();
                        }
                    }
                    //shoot up or down
                    else {
                        if (b.getX() == this.x && b.getY() == position) {
                            targets = targets + " " + b.getName();
                            b.dropBounty();
                        }
                    }

                }
            }
        }
        System.out.println(this.getName() + " shot " + targets);
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

    public int getMoney() {
        int sum = 0;
        for (Bounty b : this.bounties) {
            sum += b.value;
        }
        return sum;
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

    public void setActionsTo(List<Action> a) {
        this.actions = a;
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

