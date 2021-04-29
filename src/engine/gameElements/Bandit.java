package engine.gameElements;

import engine.utils.Action;
import engine.utils.Direction;

import java.util.ArrayList;

public class Bandit extends Character {
    final String NOM_BANDIT;
    Train train;
    ArrayList<Bounty> bounties;
    Action[] actions;

    public Bandit(String name, Train t, int abs, int ord) {
        super(name,t, abs, ord);
        this.NOM_BANDIT = name;
        this.bounties = new ArrayList<>();
        this.actions = new Action[2];
    }
    //TODO
    public void rob(){}

    public void shoot(Direction d){
        //TODO: Drop random bounty method, finish this method
        switch (d) {
            case LEFT -> {
                System.out.println( "motherfucker shoot left");
            }
            case RIGHT -> {
                System.out.println( "motherfucker shoot right");
            }
            case UP -> {
                System.out.println( "motherfucker shoot da ceiling");
            }
            case DOWN -> {
                System.out.println( "motherfucker shot da train");
            }

        }
    }
    //TODO
    public void getMoney(){}

    public void doActions(){
        for(Action a: actions){
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
    }
    //TODO: throw exception if any actions are null
    public void update(Action[] actions){

        this.actions = actions;
        doActions();
    }

}

