package engine.gameElements;

import engine.utils.Direction;

public class Character extends Entity{

    Character(String id, Train t, int abs, int ord){
        super(id,t,abs,ord);
    }

    public void move(Direction direction){

        //TODO: UNIT TESTS

        switch (direction) {
            case LEFT -> {if(this.x>0){this.x--;
                System.out.println( "motherfucker took his left");}
            }
            case RIGHT -> {if(this.x<this.train.getTrainLength()-1){this.x++;
                System.out.println( "motherfucker took his right");}
            }
            case UP -> {if(this.y==1){this.y--;
                System.out.println( "motherfucker on da ceiling");}
            }
            case DOWN -> {if(this.y==0){this.y++;
                System.out.println( "motherfucker on da train");}
            }

        }
    }
}
