package engine.gameElements;

import java.util.ArrayList;

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


    public int getTrainLength(){
        return NB_WAGONS;
    }

    public int getTrainHeight(){
        return 2;
    }
}
