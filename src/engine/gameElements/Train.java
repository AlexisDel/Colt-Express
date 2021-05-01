package engine.gameElements;

import java.util.ArrayList;
import java.util.List;

public class Train {

    private final int NB_WAGONS=4;
    private ArrayList<Entity> entities;

    public Train(){
        entities= new ArrayList<>();
    }
    public void addEntity(Entity e){
        entities.add(e);
    }
    public void removeEntity(Entity e){
        entities.remove(e);
    }

    public ArrayList<Entity> getEntities(){
        return this.entities;
    }

    public List<Bandit> getBandits(){
        List<Bandit>res= new ArrayList<>();
        for (Entity e: this.getEntities()){
            if (e instanceof Bandit){
                res.add((Bandit) e);
            }
        }
        return res;
    }

    public Marshall getMarshall(){
        Marshall res= new Marshall(this,0);
        for (Entity e: this.getEntities()) {
            if (e instanceof Marshall) {
                res= (Marshall) e;
            }
        }
        return res;
    }

    public int getTrainLength(){
        return NB_WAGONS;
    }

    public int getTrainHeight(){
        return 2;
    }
}
