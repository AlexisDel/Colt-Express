package engine.gameElements;

public class Entity {
    protected int x,y;
    protected Train train;
    private String id;

    public Entity(String id, Train t, int abs, int ord){
        this.id=id;
        this.x = abs;
        this.y = ord;
        this.train = t;
    }
    public String getID(){return this.id; }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
