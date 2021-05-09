package main.engine.gameElements;

public class Bounty extends Entity{
    String type;
    int value;

    /**
     * Returns a Bounty object
     */
    public Bounty(String type, Train train, int abs, int val){
        super(type, train, abs, 1);
        this.type=type;
        this.value=val;
    }

    /**
     * Gets the type of bounty
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Moves the bounty by accessing and changing its coordinated,
     * created for the dropBounty method in Bandit class
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void moveTo(int x, int y){
        this.x=x;
        this.y=y;
    }
}

