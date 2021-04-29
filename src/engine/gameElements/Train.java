package engine.gameElements;

public class Train {

    private final int NB_WAGONS=4;
    public Bandit[][] board;

    public Train(){
        board =  new Bandit[2][NB_WAGONS];
    }

    public int get_NB_WAGONS() {
        return NB_WAGONS;
    }

    public int getTrainLength(){
        return board[0].length;
    }

    public int getTrainHeight(){
        return board.length;
    }
}
