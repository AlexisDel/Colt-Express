package main.engine.gameElements;

import main.engine.utils.Direction;

public class Character extends Entity {

    private final String name;

    /**
     * Returns a Character Object with a name,
     * mainly used as parent class (To factorize code) and will not be instantiated at any point
     */
    Character(String type, String name, Train train, int abs, int ord) {
        super(type, train, abs, ord);
        this.name = name;
    }

    /**
     * Returns a Character Object without a name (name being set to the same String as type)
     */
    Character(String type, Train train, int abs, int ord) {
        super(type, train, abs, ord);
        this.name = type;
    }

    /**
     * Gets the name of the character
     *
     * @return String name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Moves a character in a specific direction by accessing and modifying its coordinates
     *
     * @param direction enumerate type Direction
     */
    public void move(Direction direction) {

        //disables Marshall notifications in the run terminal
        boolean notify = true;
        if (this.name == "Marshall") {
            notify = false;
        }

        //the main code of the method
        switch (direction) {
            case LEFT -> {
                if (this.x > 0) {
                    this.x--;
                    if (notify)
                        System.out.println(this.name + " has moved left");
                }
            }
            case RIGHT -> {
                if (this.x < this.train.getTrainLength() - 1) {
                    this.x++;
                    if (notify)
                        System.out.println(this.name + " has moved right");
                }
            }
            case UP -> {
                if (this.y == 1) {
                    this.y--;
                    if (notify)
                        System.out.println(this.name + " has moved up");
                }
            }
            case DOWN -> {
                if (this.y == 0) {
                    this.y++;
                    if (notify)
                        System.out.println(this.name + " has moved down");
                }
            }

        }
    }
}
