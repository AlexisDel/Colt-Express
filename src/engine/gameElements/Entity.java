package engine.gameElements;

public class Entity {
    protected int x, y;
    protected String type;
    protected Train train;

    /**
     * Returns an Entity Object,
     * all objects on a train are entities,
     * mainly used as parent class (To factorize code) and will not be instantiated at any point
     */
    public Entity(String type, Train train, int abs, int ord) {
        this.type = type;
        this.x = abs;
        this.y = ord;
        this.train = train;
    }
    /**
     * Gets the type of the entity
     * @return String type
     */
    public String getType() {
        return type;
    }
    /**
     * Gets the x coordinate of the entity
     * @return int x coordinate
     */
    public int getX() { return this.x; }

    /**
     * Gets the y coordinate of the entity
     * @return int y coordinate
     */
    public int getY() {
        return this.y;
    }

}
