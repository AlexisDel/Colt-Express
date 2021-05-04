package engine.gameElements;

public class Bounty extends Entity{
    String type;
    int value;

    public Bounty(String type, Train train, int abs, int val){
        super(type, train, abs, 1);
        this.type=type;
        this.value=val;
    }

    public String getType() {
        return type;
    }

    public void moveTo(int x, int y){
        this.x=x;
        this.y=y;
    }
}

