package main.engine;

import main.controller.GameController;
import main.engine.gameElements.Bandit;
import main.engine.gameElements.Bounty;
import main.engine.gameElements.Marshall;
import main.engine.gameElements.Train;
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

    private Train train;
    private boolean actionButtonPushed;

    public GameEngine() {
        this.actionButtonPushed = false;
        this.isGameFinished = false;
        this.gameState = GameState.PLANNING;

        this.train = new Train();
        //Adds players in the String list, setups the marshall and the bounty
        //TODO: ask for input for the player names? No names also possible by only asking nbr of players
        String[] players = new String[]{"Brown", "Black"};
        setupEntities(players);

        this.gameController = new GameController(this);
        this.gameDisplay = new GameDisplay(this);

    }

    public Train getTrain() {
        return this.train;
    }

    public boolean setActionButtonPushed(boolean b) {
        return this.actionButtonPushed = b;
    }

    public void setupEntities(String[] playerNames) {
        //Spawns the bounty
        genBounty();
        //Setup players
        for (int i = 0; i < playerNames.length; i++) {
            Bandit player = new Bandit(("Bandit" + i), playerNames[i], this.train, i % this.train.getTrainLength(), 0);
            this.train.addEntity(player);
        }

        //Spawns the marshall
        Marshall marshall = new Marshall(this.train, this.train.getTrainLength() - 1);
        this.train.addEntity(marshall);


    }

    //This function randomly generates the bounty inside the train
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


    public boolean allActionsExecuted(List<Bandit> b) {
        for (Bandit bandit : b) {
            if (bandit.getActions().size() != 0) {
                return false;
            }
        }
        return true;
    }

    public void setupPlayersActions(List<Action> actionList) {
        if (actionList.size() != 0) {

            int NB_PLAYERS = this.train.getBandits().size();
            for (int i = 0; i < NB_PLAYERS; i++) {
                this.train.getBandits().get(i).addAction(actionList.get(i * NB_PLAYERS + 0));
                this.train.getBandits().get(i).addAction(actionList.get(i * NB_PLAYERS + 1));
            }
        }
    }

    private void handleButtons() {
        if (gameController.getActions().size() < 4) {
            gameDisplay.buttons.enableActionsButtons();
            gameDisplay.buttons.disableActionButton();
        }
        else {
            gameDisplay.buttons.disableActionsButtons();
            gameDisplay.buttons.enableActionButton();
        }
    }

    public void update() {
        if (this.gameState == GameState.ACTION) {
            if (actionButtonPushed) {
                for (Bandit b : this.train.getBandits()) {
                    b.update();
                    this.train.getMarshall().catchBandit();
                }
                if (allActionsExecuted(this.train.getBandits())) {
                    gameState = GameState.PLANNING;
                    this.gameController.resetActions();
                }
                actionButtonPushed = false;
            }
        } else {
            setupPlayersActions(gameController.getActions());
        }
        handleButtons();
        gameDisplay.update();
    }
}
