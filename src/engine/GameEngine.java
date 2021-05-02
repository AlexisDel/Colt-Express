package engine;

import controller.GameController;
import engine.gameElements.Bandit;
import engine.gameElements.Bounty;
import engine.gameElements.Marshall;
import engine.gameElements.Train;
import engine.GameState;
import graphics.GameDisplay;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class GameEngine {

    public GameController gameController;
    public GameDisplay gameDisplay;

    public boolean isGameFinished;
    public GameState gameState;

    private Train train;

    public GameEngine() {

        this.isGameFinished = false;
        this.gameState = GameState.PLANNING;

        this.train = new Train();
        //Adds players in the String list, setups the marshall and the bounty
        //TODO: ask for input for the player names? No names also possible by only asking nbr of players
        String[] players= new String[]{"CrocoMechant","Fantiks"};
        setupEntities(players);

        this.gameController = new GameController(this);
        this.gameDisplay = new GameDisplay(this);

    }

    public Train getTrain(){return this.train;}

    public void setupEntities(String[] playerNames){
        //Spawns the bounty
        genBounty();
        //Setup players
        for(String name: playerNames){
            Bandit player = new Bandit(name,this.train, 0,0);
            this.train.addEntity(player);
        }
        //Spawns the marshall
        Marshall marshall= new Marshall(this.train, this.train.getTrainLength()-1);
        this.train.addEntity(marshall);


    }
    //This function randomly generates the bounty inside the train
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
    

    public boolean actionsCompleted(List<Bandit>b){
        for(Bandit bandit: b){
            if(!bandit.getActionsCompleted()){return false;}
        }
        return true;
    }

    public void update() {
        gameDisplay.update();

        if (this.gameState == GameState.ACTION) {
            //2PLAYERS for(Bandit b: this.train.getBandits()){b.update();}
            this.train.getBandits().get(0).update();

            if(actionsCompleted(this.train.getBandits())){
                gameState = GameState.PLANNING;
                this.gameController.resetActionsQueue();
            }
            else{
                this.train.getMarshall().setNERVOSITE_MARSHALL((float) 0.3);
                this.train.getMarshall().update();
                gameDisplay.update();
                }
        }
        else{
            //2PLAYERS for(Bandit b: this.train.getBandits()){b.setActionsTo(gameController.getActions());}
            this.train.getBandits().get(0).setActionsTo(gameController.getActions());

            //allows for the marshall to move a bit slower while players are planning
            this.train.getMarshall().setNERVOSITE_MARSHALL((float) 0.2);
            this.train.getMarshall().update();
            gameDisplay.update();
        }

    }

}
