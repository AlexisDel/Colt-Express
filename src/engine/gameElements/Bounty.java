package engine.gameElements;

public class Bounty extends Entity{
    String type;
    int value;

    public Bounty(Train t, int abs, String type, int val){
        super(type,t, abs, 1);
        this.type=type;
        this.value=val;
    }

    public void moveTo(int x, int y){
        this.x=x;
        this.y=y;
    }
}

