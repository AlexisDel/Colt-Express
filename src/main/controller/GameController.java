package main.controller;

import main.engine.GameEngine;
import main.engine.utils.Action;
import main.engine.utils.GameState;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final int NB_ACTIONS;
    private final int NB_PLAYERS;

    private final GameEngine gameEngine;

    private final List<Action> actions;
    private boolean actionsReady;

    /**
     * Return a GameController object
     * Manages user inputs
     *
     * @param gameEngine
     */
    public GameController(GameEngine gameEngine) {

        this.gameEngine = gameEngine;
        this.actions = new ArrayList<>();
        this.NB_PLAYERS = this.gameEngine.getTrain().getBandits().size();
        this.NB_ACTIONS = 2 * NB_PLAYERS;   //2 Actions per Bandit
        this.actionsReady = false;
    }

    /**
     * Adds an action to the global actions list
     *
     * @param action
     */
    private void addAction(Action action) {

        if (this.actions.size() < NB_ACTIONS) {
            this.actions.add(action);
        } else {
            System.out.println("No more than" + NB_ACTIONS + "actions");
        }

        // Handles the actionsReady variable
        if (this.actions.size() == this.NB_ACTIONS) {
            this.actionsReady = true;
        }
    }

    /**
     * Empties the global actions list
     */
    public void resetActions() {
        this.actions.clear();
        // Resets the actionsReady variable
        this.actionsReady = false;
    }

    /**
     * Returns the global actions list or an empty list depending on the actionsReady variable
     *
     * @return List<Action>
     */
    public List<Action> getActions() {
        if (this.actionsReady) {
            return this.actions;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Returns the number of the player which has to play
     *
     * @return int
     */
    private int getPlayerTurn() {
        return (actions.size() < 2) ? 1 : 2;
    }

    /**
     * Returns a string indicating which player has to play
     *
     * @return String
     */
    public String getPlayerTurnAsString() {
        if (getClickLeftPerPlayer() != 0) {
            if(getPlayerTurn()==1) return "Brown Bandit's turn";
            else return "Black Bandit's turn";
        } else {
            return null;
        }
    }

    /**
     * Returns the number of actions to determine according to the player who has to play
     *
     * @return int
     */
    private int getClickLeftPerPlayer() {
        if (getPlayerTurn() == 1) {
            return 2 - actions.size();
        } else {
            return 4 - actions.size();
        }
    }

    /**
     * Returns a string indicating how much actions the player that has to play has to determine
     *
     * @return String
     */
    public String getClickLeftPerPlayerAsString() {
        if (getClickLeftPerPlayer() == 2) {
            return "2 actions to determine";
        } else {
            return "1 action to determine";
        }
    }

    public void goUp() {
        addAction(Action.MOVE_UP);
    }

    public void goDown() {
        addAction(Action.MOVE_DOWN);
    }

    public void goLeft() {
        addAction(Action.MOVE_LEFT);
    }

    public void goRight() {
        addAction(Action.MOVE_RIGHT);
    }

    public void shootUp() {
        addAction(Action.SHOOT_UP);
    }

    public void shootDown() {
        addAction(Action.SHOOT_DOWN);
    }

    public void shootLeft() {
        addAction(Action.SHOOT_LEFT);
    }

    public void shootRight() {
        addAction(Action.SHOOT_RIGHT);
    }

    public void rob() {
        addAction(Action.ROB);
    }

    /**
     * Enable the action state depending on the actionsReady variable
     */
    public void action() {
        // Only allows Action State if the actions are ready(there are 2 actions)
        if (this.actionsReady) {
            gameEngine.gameState = GameState.ACTION;
            gameEngine.setActionButtonPushed(true);
        }
    }
}