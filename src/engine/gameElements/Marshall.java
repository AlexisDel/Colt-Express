package engine.gameElements;

import engine.utils.Direction;

import java.util.Random;

public class Marshall extends Character {
    private float NERVOSITE_MARSHALL;

    public Marshall(Train t, int abs){
        super("Marshall",t, abs, 1);
        this.NERVOSITE_MARSHALL= (float) 0.3;
    }
    //TODO
    public void autoMove(){
        Random r= new Random();
        int probability= r.nextInt(100);
        boolean randomDirection= r.nextBoolean();
        if(probability<=NERVOSITE_MARSHALL*100) {
           if(randomDirection){this.move(Direction.LEFT);}
           else{this.move(Direction.RIGHT);}
        }
    }
    //TODO
    public void catchBandit(){
        for(Bandit b: this.train.getBandits()){
            if(this.x==b.getX()&& b.getY()==1){
                //TODO: drops 1 random bounty
                b.move(Direction.UP);
                System.out.println("The Marshall caught "+b.getID());
            }
        }
    }

    public void update(){
        catchBandit();
        autoMove();
        catchBandit();
    }
}