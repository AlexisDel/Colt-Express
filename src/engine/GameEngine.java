package engine;

import controller.GameController;
import engine.gameElements.Bandit;
import engine.gameElements.Train;
import engine.utils.GameState;
import graphics.GameDisplay;

public class GameEngine {

    public GameController gameController;
    public GameDisplay gameDisplay;

    public boolean isGameFinished;
    public GameState gameState;

    public Train train;
    private Bandit CrocoMechant;

    public GameEngine() {

        this.isGameFinished = false;
        this.gameState = GameState.PLANNING;

        this.train = new Train();
        this.CrocoMechant = new Bandit("CrocoMechant", this.train, 0,0);

        this.gameController = new GameController(this);
        this.gameDisplay = new GameDisplay(this);

    }

    public void update() {
        if (this.gameState == GameState.ACTION){
            CrocoMechant.update(gameController.actions);
            gameController.resetActionsQueue();
            gameState = GameState.PLANNING;
        }
        gameDisplay.update();
    }
}
