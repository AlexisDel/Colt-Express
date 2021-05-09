package main.engine.gameElements;

import java.util.ArrayList;
import java.util.List;

public class Train {

    private final int NB_WAGONS = 4;
    private final ArrayList<Entity> entities;

    /**
     * Returns a Train object which only has a list of Entity on board
     */
    public Train() {
        entities = new ArrayList<>();
    }

    /**
     * Ads an entity to the train's entity list
     */
    public void addEntity(Entity e) {
        entities.add(e);
    }

    /**
     * Removes an entity to the train's entity list
     */
    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    /**
     * Gets the train's entity list
     *
     * @return List<Entity>
     */
    public List<Entity> getEntities() {
        return this.entities;
    }

    /**
     * Gets a list with the bandits in the train's entity list
     *
     * @return List<Bandits>
     */
    public List<Bandit> getBandits() {
        List<Bandit> res = new ArrayList<>();
        for (Entity e : this.getEntities()) {
            if (e instanceof Bandit) {
                res.add((Bandit) e);
            }
        }
        return res;
    }

    /**
     * Gets the Marshall (any number of marshall) in the train's entity list
     *
     * @return Marshall
     */
    public Marshall getMarshall() {
        Marshall res = new Marshall(this, 0);
        for (Entity e : this.getEntities()) {
            if (e instanceof Marshall) {
                res = (Marshall) e;
            }
        }
        return res;
    }

    /**
     * Gets a list with all the bounty at a specific x,y coordinates in the train's entity list
     *
     * @return List<Bounty>
     */
    public List<Bounty> getBountyAt(int x, int y) {
        List<Bounty> res = new ArrayList<>();
        for (Entity e : this.getEntities()) {
            if (e instanceof Bounty && e.getX() == x && e.getY() == y) {
                res.add((Bounty) e);
            }
        }
        return res;
    }

    /**
     * Gets the train length
     *
     * @return int number of wagons
     */
    public int getTrainLength() {
        return NB_WAGONS;
    }

    /**
     * Gets the train Height
     *
     * @return int number of wagons
     */
    public int getTrainHeight() {
        return 2;
    }
}
