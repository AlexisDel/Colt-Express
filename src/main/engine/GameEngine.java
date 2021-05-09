package main.engine;

import main.controller.GameController;
import main.engine.gameElements.*;
import main.engine.utils.Action;
import main.engine.utils.GameState;
import main.graphics.GameDisplay;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class GameEngine {

    public GameController gameController;
    public GameDisplay gameDisplay;

    public boolean isGameFinished;
    public GameState gameState;

    private final Train train;
    private boolean actionButtonPushed;

    /**
     * Returns GameEngine object
     * Manages game logic and mechanics
     */
    public GameEngine() {
        this.actionButtonPushed = false;
        this.isGameFinished = false;
        this.gameState = GameState.PLANNING;
        this.train = new Train();
        //Adds players with names in the String list, setups the marshall and the bounty
        String[] players = new String[]{"Brown", "Black"};
        setupEntities(players);
        this.gameController = new GameController(this);
        this.gameDisplay = new GameDisplay(this);
    }

    /**
     * Gets the GameEngine's train
     *
     * @return Train
     */
    public Train getTrain() {
        return this.train;
    }

    /**
     * Sets the actionButtonPushed attribute to a specific boolean
     */
    public boolean setActionButtonPushed(boolean b) {
        return this.actionButtonPushed = b;
    }

    /**
     * Sets up all the entities (creates them and adds to the train entity list)
     */
    public void setupEntities(String[] playerNames) {
        //Spawns the bounty
        //genBounty();
        Bounty treasure= new Bounty("Treasure", this.train, this.train.getTrainLength()-1,1000);
        this.train.addEntity(treasure);
        //Setup all player's bandits
        for (int i = 0; i < playerNames.length; i++) {
            Bandit player = new Bandit(("Bandit" + i), playerNames[i], this.train, i % this.train.getTrainLength(), 0);
            this.train.addEntity(player);
        }
        //Spawns the marshall
        Marshall marshall = new Marshall(this.train, this.train.getTrainLength() - 1);
        this.train.addEntity(marshall);
    }

    /**
     * Method randomly generates all the bounty inside the train
     */
    public void genBounty() {
        //Locomotive bounty
        Bounty treasure = new Bounty("Treasure", this.train, train.getTrainLength() - 1, 1000);
        this.train.addEntity(treasure);
        //Other bounty
        int randomNum1 = 0;
        int randomNum2 = 0;
        int randValue = 0;
        //For each wagon
        for (int x = 0; x < train.getTrainLength() - 1; x++) {
            randomNum1 = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            //Random num of jewelry and bags
            for (int k = 0; k < randomNum1; k++) {
                randValue = ThreadLocalRandom.current().nextInt(0, 500 + 1);
                Bounty bag = new Bounty("Bag", this.train, x, randValue);
                this.train.addEntity(bag);
            }
            randomNum2 = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            for (int k = 0; k < randomNum2 - randomNum1; k++) {
                Bounty jewelry = new Bounty("Jewel", this.train, x, 500);
                this.train.addEntity(jewelry);
            }
        }
    }

    /**
     * Verifies for each bandit if all actions in his actions list have been executed (actions list is empty)
     *
     * @param b list of bandits
     * @return boolean
     */
    public boolean allActionsExecuted(List<Bandit> b) {
        for (Bandit bandit : b) {
            if (bandit.getActions().size() != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gives each player's bandit its actions list
     * from an input actionList (passed by gameController in GameEngine update method)
     *
     * @param actionList the action list containing all actions for all player's bandits
     */
    public void setupPlayersActions(List<Action> actionList) {
        if (actionList.size() != 0) {

            int NB_PLAYERS = this.train.getBandits().size();
            for (int i = 0; i < NB_PLAYERS; i++) {
                this.train.getBandits().get(i).addAction(actionList.get(i * NB_PLAYERS + 0));
                this.train.getBandits().get(i).addAction(actionList.get(i * NB_PLAYERS + 1));
            }
        }
    }

    /**
     * Enables or disables the action/planning control buttons (used by the graphic classes)
     */
    private void handleButtons() {
        if (gameController.getActions().size() < 4) {
            gameDisplay.buttons.enableActionsButtons();
            gameDisplay.buttons.disableActionButton();
        } else {
            gameDisplay.buttons.disableActionsButtons();
            gameDisplay.buttons.enableActionButton();
        }
    }
    /**
     * Verifies if the game has finished by verifying endgame conditions
     */
    public void verifyEndConditions(){
        int countBounties=0;
        for(Entity e: this.train.getEntities()){
            if(e instanceof Bounty){
                countBounties++;
            }
        }
        if(countBounties==0){this.isGameFinished=true;}
    }

    /**
     * Updates the game engine, main update method repeated in each game loop in the Game class
     */
    public void update() {
        if (this.gameState == GameState.ACTION) {
            //Action State
            if (actionButtonPushed) {
                for (Bandit b : this.train.getBandits()) {
                    b.update();
                    this.train.getMarshall().catchBandit();
                    verifyEndConditions();
                }
                if (allActionsExecuted(this.train.getBandits())) {
                    gameState = GameState.PLANNING;
                    this.gameController.resetActions();
                }
                actionButtonPushed = false;
            }
        } else {
            //Planning State
            setupPlayersActions(gameController.getActions());
        }
        handleButtons();
        gameDisplay.update();
    }
}
