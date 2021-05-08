package engine.gameElements;

import engine.utils.Direction;

public class Character extends Entity {

    private String name;

    Character(String type, String name, Train train, int abs, int ord) {
        super(type, train, abs, ord);
        this.name = name;
    }

    Character(String type, Train train, int abs, int ord) {
        super(type, train, abs, ord);
        this.name = type;
    }

    public String getName() {
        return name;
    }

    public void move(Direction direction) {

        //TODO: UNIT TESTS
        //disables Marshall notifications
        boolean notify=true;
        if(this.name=="Marshall"){notify=false;}

        switch (direction) {
            case LEFT -> {
                if (this.x > 0) {
                    this.x--;
                    if(notify)
                    System.out.println(this.name + " has moved left");
                }
            }
            case RIGHT -> {
                if (this.x < this.train.getTrainLength() - 1) {
                    this.x++;
                    if(notify)
                    System.out.println(this.name + " has moved right");
                }
            }
            case UP -> {
                if (this.y == 1) {
                    this.y--;
                    if(notify)
                    System.out.println(this.name + " has moved up");
                }
            }
            case DOWN -> {
                if (this.y == 0) {
                    this.y++;
                    if(notify)
                    System.out.println(this.name + " has moved down");
                }
            }

        }
    }
}
