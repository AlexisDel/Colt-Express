package engine.gameElements;

public class Entity {
    protected int x,y;
    Train train;

    public Entity(Train t, int abs, int ord){
        this.x = abs;
        this.y = ord;
        this.train = t;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
