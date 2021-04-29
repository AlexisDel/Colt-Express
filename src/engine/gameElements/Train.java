package engine.gameElements;

public class Train {

    final int NB_WAGONS=4;
    protected Bandit[][] board;

    public Train(){
        board =  new Bandit[2][NB_WAGONS];
    }
}
