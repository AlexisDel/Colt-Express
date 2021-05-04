package engine.gameElements;

public class Entity {
    protected int x, y;
    protected String type;
    protected Train train;

    public Entity(String type, Train train, int abs, int ord) {
        this.type = type;
        this.x = abs;
        this.y = ord;
        this.train = train;
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
