package engine.gameElements;

import engine.utils.Action;
import engine.utils.Direction;

import java.util.ArrayList;
import java.util.List;

public class Bandit extends Character {
    final String NOM_BANDIT;
    private Train train;
    private ArrayList<Bounty> bounties;
    private List <Action> actions;
    private boolean actionsCompleted;

    public Bandit(String name, Train t, int abs, int ord) {
        super(name,t, abs, ord);
        this.NOM_BANDIT = name;
        this.bounties = new ArrayList<>();
        this.actions= new ArrayList<>();
        //actions list init as empty so all actions completed
        this.actionsCompleted=true;

    }

    public boolean getActionsCompleted(){return this.actionsCompleted;}
    public List<Action> getActions(){return this.actions;}

    //TODO
    public void rob(){}

    public void shoot(Direction d){
        //TODO: Drop random bounty method, finish this method
        switch (d) {
            case LEFT -> {
                System.out.println( this.getID()+" shoot left");
            }
            case RIGHT -> {
                System.out.println( this.getID()+" shoot right");
            }
            case UP -> {
                System.out.println( this.getID()+" shoot da ceiling");
            }
            case DOWN -> {
                System.out.println( this.getID()+" shot da train");
            }

        }
    }
    //TODO
    public void getMoney(){}

    public void doAction(Action a){

            switch (a) {
                case MOVE_UP -> { this.move(Direction.UP);
                }
                case MOVE_DOWN -> { this.move(Direction.DOWN);
                }
                case MOVE_RIGHT -> { this.move(Direction.RIGHT);
                }
                case MOVE_LEFT -> { this.move(Direction.LEFT);
                }
                case SHOOT_UP -> { this.shoot(Direction.UP);
                }
                case SHOOT_DOWN -> { this.shoot(Direction.DOWN);
                }
                case SHOOT_RIGHT -> { this.shoot(Direction.RIGHT);
                }
                case SHOOT_LEFT -> { this.shoot(Direction.LEFT);
                }
                case ROB -> { this.rob();
                }


        }
    }

    public void setActionTo(List<Action>a){
        this.actions=a;
        this.actionsCompleted = false;
    }




    //TODO: throw exception if any actions are null
    public void update(){
        //if the list is not empty
        if(this.actions.size()>0) {
            //do last action in queue
            doAction(this.actions.get(0));
            //remove last action in queue
            this.actions.remove(0);
        }
        //if the list is empty, all actions should have been executed
        else{ this.actionsCompleted=true;}
    }

}

