package controller;

import engine.utils.Action;
import engine.GameEngine;
import engine.utils.GameState;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private int NB_ACTIONS=2;

    private GameEngine gameEngine;

    private List<Action> actions;

    private boolean actionsReady;

    public GameController(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        this.actions = new ArrayList<>();
        this.actionsReady=false;
    }

    public List<Action> getActions(){
        return this.actions;
    }

    private void addActionToQueue(Action action){
        if(this.actions.size()<NB_ACTIONS){
            actions.add(action);
            if(this.actions.size()==NB_ACTIONS){this.actionsReady=true;}
        }
    }

    public void resetActionsQueue(){
        actions.clear();
        this.actionsReady=false;
    }

    public void goUp(){
        addActionToQueue(Action.MOVE_UP);
    }

    public void goDown(){
        addActionToQueue(Action.MOVE_DOWN);
    }

    public void goLeft(){
        addActionToQueue(Action.MOVE_LEFT);
    }

    public void goRight(){
        addActionToQueue(Action.MOVE_RIGHT);
    }

    public void shootUp(){
        addActionToQueue(Action.SHOOT_UP);
    }

    public void shootDown(){
        addActionToQueue(Action.SHOOT_DOWN);
    }

    public void shootLeft(){
        addActionToQueue(Action.SHOOT_LEFT);
    }

    public void shootRight(){
        addActionToQueue(Action.SHOOT_RIGHT);
    }

    public void rob(){
        addActionToQueue(Action.ROB);
    }

    public void action(){
        //Only allows Action State if the actions are ready(there are 2 actions)
        if(this.actionsReady){ gameEngine.gameState = GameState.ACTION;}
        else{this.actions.clear();
            System.out.println("Exactly "+ NB_ACTIONS+ " actions are allowed per player!");}
    }

}