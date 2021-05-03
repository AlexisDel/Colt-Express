package controller;

import engine.gameElements.Bandit;
import engine.utils.Action;
import engine.GameEngine;
import engine.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

    private GameEngine gameEngine;
    private List<Action>actions;
    private boolean actionsReady;
    private int NB_PLAYERS;
    private final int NB_ACTIONS;

    public GameController(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        this.actions= new ArrayList<>();
        this.NB_PLAYERS= this.gameEngine.getTrain().getBandits().size();
        //2 Actions per Bandit
        this.NB_ACTIONS=2*NB_PLAYERS;
        this.actionsReady=false;
    }


    private void addAction(Action action){
        if(this.actions.size()<NB_ACTIONS){
            this.actions.add(action);}

        if(this.actions.size()==this.NB_ACTIONS){this.actionsReady=true;}
    }

    public void resetActions(){
        this.actions.clear();
        this.actionsReady=false;
    }

    public List<Action> getActions(){
        if(this.actionsReady){
            System.out.println("giving actions");
            return this.actions;
            }
        else{return new ArrayList<>();}
    }

    public void goUp(){
        addAction(Action.MOVE_UP);
    }

    public void goDown(){
        addAction(Action.MOVE_DOWN);
    }

    public void goLeft(){
        addAction(Action.MOVE_LEFT);
    }

    public void goRight(){
        addAction(Action.MOVE_RIGHT);
    }

    public void shootUp(){
        addAction(Action.SHOOT_UP);
    }

    public void shootDown(){
        addAction(Action.SHOOT_DOWN);
    }

    public void shootLeft(){
        addAction(Action.SHOOT_LEFT);
    }

    public void shootRight(){
        addAction(Action.SHOOT_RIGHT);
    }

    public void rob(){
        addAction(Action.ROB);
    }

    public void action(){
        //Only allows Action State if the actions are ready(there are 2 actions)
        if(this.actionsReady){ gameEngine.gameState = GameState.ACTION;}
        else{;
            System.out.println("Exactly "+ NB_ACTIONS+ " actions are allowed per player!");}
    }

}