package controller;

import engine.utils.Action;
import engine.GameEngine;
import engine.utils.GameState;

public class GameController {

    private GameEngine gameEngine;

    public Action[] actions = new Action[2];

    public GameController(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    private void addActionToQueue(Action action){
        this.actions[0] = this.actions[1];
        this.actions[1] = action;

    }

    public void resetActionsQueue(){
        this.actions[0] = null;
        this.actions[1] = null;
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
        gameEngine.gameState = GameState.ACTION;
    }

}