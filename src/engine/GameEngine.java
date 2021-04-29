package engine;

import controller.GameController;
import engine.gameElements.Bandit;
import engine.gameElements.Bounty;
import engine.gameElements.Marshall;
import engine.gameElements.Train;
import engine.utils.GameState;
import graphics.GameDisplay;
import java.util.concurrent.ThreadLocalRandom;


public class GameEngine {

    public GameController gameController;
    public GameDisplay gameDisplay;

    public boolean isGameFinished;
    public GameState gameState;

    public Train train;
    private Bandit[] bandits;

    public GameEngine() {

        this.isGameFinished = false;
        this.gameState = GameState.PLANNING;

        this.train = new Train();
        //Adds players in the String list, setups the marshall and the bounty
        String[] players= new String[]{"CrocoMechant","Fantiks"};
        bandits= new Bandit[players.length];
        setupEntities(players);

        this.gameController = new GameController(this);
        this.gameDisplay = new GameDisplay(this);

    }
    public void setupEntities(String[] playerNames){
        //Setup players
        for(int i=0; i<playerNames.length;i++){
            Bandit player = new Bandit(playerNames[i] , this.train, 0,0);
            this.train.addEntity(player);
            this.bandits[i]= player;

        }
        //Spawns the marshall
        Marshall marshall= new Marshall(this.train, this.train.getTrainLength()-1);
        this.train.addEntity(marshall);
        //Spawns the bounty
        genBounty();

    }
    public void genBounty(){
        //Locomotive bounty
        Bounty treasure= new Bounty(this.train, train.getTrainLength()-1, "Treasure",1000);
        this.train.addEntity(treasure);
        //Other bounty
        int randomNum1=0;
        int randomNum2=0;
        int randValue=0;
        //For each wagon
        for(int x=0; x< train.getTrainLength()-1;x++){
             randomNum1= ThreadLocalRandom.current().nextInt(1, 4 + 1);
             //Random num of jewelry and bags
            for(int k=0; k< randomNum1;k++){
                randValue=ThreadLocalRandom.current().nextInt(0, 500 + 1);
                Bounty bag= new Bounty(this.train, x, "Bag",randValue);
                this.train.addEntity(bag);
            } randomNum2=ThreadLocalRandom.current().nextInt(1, 4 + 1);
            for(int k=0; k< randomNum2-randomNum1;k++){
                Bounty jewelry= new Bounty(this.train, x, "Jewel",500);
                this.train.addEntity(jewelry);
            }
        }
    }

    public void update() {
        if (this.gameState == GameState.ACTION){
            //Croco bandit selected
            //TODO: Enable more players (Changes needed elsewhere)
            bandits[0].update(gameController.actions);
            gameController.resetActionsQueue();
            gameState = GameState.PLANNING;
        }
        gameDisplay.update();
    }
}
