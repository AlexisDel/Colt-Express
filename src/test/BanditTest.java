package test;

import main.engine.gameElements.Marshall;
import main.engine.utils.Action;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.engine.gameElements.Bandit;
import main.engine.gameElements.Bounty;
import main.engine.gameElements.Train;
import main.engine.utils.Direction;

/**
 * Unit tests for all bandit's actions (Rob, shoot, move) and Marshall catch method
 */
class BanditTest {

    /**
     * Tests the rob action for the bandit and other complementary methods
     */
    @Test
    void rob() {
        Train testTrain = new Train();
        Bandit testSubject = new Bandit("bandit1", "Pierre", testTrain, 0, 1);
        Bounty b1 = new Bounty("test", testTrain, 0, 1000);
        Bounty b2 = new Bounty("test", testTrain, 0, 1000);

        testTrain.addEntity(testSubject);
        testTrain.addEntity(b1);
        testTrain.addEntity(b2);

        assertEquals(0, testSubject.getBounty().size());

        testSubject.rob();
        assertEquals(1, testSubject.getBounty().size());

        testSubject.rob();
        assertEquals(2, testSubject.getBounty().size());

        testTrain.addEntity(b1);
        testTrain.addEntity(b2);
        testSubject.rob();
        testSubject.rob();
        assertEquals(4, testSubject.getBounty().size());

        testSubject.dropBounty();
        testSubject.dropBounty();
        testSubject.dropBounty();
        assertEquals(1, testSubject.getBounty().size());

        testSubject.dropBounty();
        assertEquals(0, testSubject.getBounty().size());

        testSubject.dropBounty();
        assertEquals(0, testSubject.getBounty().size());

        //Scenario: Bandits move and then robs some bounty
        Train testTrain2 = new Train();
        Bandit testSubject1 = new Bandit("bandit1", "Pierre", testTrain2, 0, 0);
        Bandit testSubject2 = new Bandit("bandit2", "Kemper", testTrain2, 2, 0);

        Bounty b12 = new Bounty("test", testTrain2, 0, 1000);
        Bounty b22 = new Bounty("test", testTrain2, 2, 1000);

        testTrain2.addEntity(testSubject1);
        testTrain2.addEntity(testSubject2);

        testTrain2.addEntity(b12);
        testTrain2.addEntity(b22);

        testSubject1.rob();
        testSubject2.rob();

        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());

        testSubject1.rob();
        testSubject2.rob();
        testSubject1.rob();
        testSubject2.rob();

        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());

        testSubject1.move(Direction.DOWN);
        testSubject2.move(Direction.DOWN);
        testSubject1.rob();
        testSubject2.rob();

        assertEquals(1, testSubject1.getBounty().size());
        assertEquals(1, testSubject2.getBounty().size());
        //New scenario
        Train testTrain3 = new Train();
        Bandit testSubject3 = new Bandit("bandit1", "Pierre", testTrain3, 2, 0);
        Bandit testSubject4 = new Bandit("bandit2", "Kemper", testTrain3, 2, 0);

        Bounty b123 = new Bounty("test", testTrain3, 2, 500);
        Bounty b223 = new Bounty("test", testTrain3, 2, 1000);

        testTrain3.addEntity(testSubject3);
        testTrain3.addEntity(testSubject4);

        testTrain3.addEntity(b123);
        testTrain3.addEntity(b223);

        testSubject3.move(Direction.DOWN);
        testSubject4.move(Direction.DOWN);
        testSubject3.rob();
        testSubject4.rob();

        assertEquals(1, testSubject3.getBounty().size());
        assertEquals(1, testSubject4.getBounty().size());
        assertTrue(testSubject3.getMoney() == 500 || testSubject3.getMoney() == 1000);
        assertTrue(testSubject4.getMoney() == 500 || testSubject4.getMoney() == 1000);
        testSubject3.dropBounty();
        testSubject4.dropBounty();
        assertEquals(0, testSubject3.getBounty().size());
        assertEquals(0, testSubject4.getBounty().size());
        assertEquals(0, testSubject3.getMoney());
        assertEquals(0, testSubject4.getMoney());


    }

    /**
     * Tests the shoot action for the bandit and other complementary methods
     */
    @Test
    void shoot() {
        Train testTrain = new Train();
        Bandit testSubject1 = new Bandit("bandit1", "Manson", testTrain, 0, 1);
        Bandit testSubject2 = new Bandit("bandit1", "Kemper", testTrain, 2, 1);
        testTrain.addEntity(testSubject1);
        testTrain.addEntity(testSubject2);
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());
        assertEquals(5, testSubject1.getBullets());
        assertEquals(5, testSubject2.getBullets());

        testSubject1.shoot(Direction.RIGHT);
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());
        assertEquals(4, testSubject1.getBullets());
        assertEquals(5, testSubject2.getBullets());

        testSubject2.shoot(Direction.LEFT);
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());
        assertEquals(4, testSubject1.getBullets());
        assertEquals(4, testSubject2.getBullets());

        Bounty b1 = new Bounty("treasure", testTrain, 0, 1000);
        Bounty b2 = new Bounty("treasure", testTrain, 2, 1000);
        Bounty b12 = new Bounty("bag", testTrain, 0, 200);
        Bounty b22 = new Bounty("bag", testTrain, 2, 100);
        testTrain.addEntity(b1);
        testTrain.addEntity(b2);
        testTrain.addEntity(b12);
        testTrain.addEntity(b22);

        testSubject1.rob();
        testSubject2.rob();
        testSubject1.rob();
        testSubject2.rob();
        //Move both bandits to same position inside train:
        testSubject1.move(Direction.RIGHT);
        testSubject1.move(Direction.RIGHT);
        testSubject1.shoot(Direction.DOWN);
        assertEquals(2, testSubject1.getX());
        assertEquals(2, testSubject2.getX());
        assertEquals(1, testSubject1.getY());
        assertEquals(1, testSubject2.getY());

        assertEquals(2, testSubject1.getBounty().size());
        assertEquals(1, testSubject2.getBounty().size());
        assertEquals(3, testSubject1.getBullets());
        assertEquals(4, testSubject2.getBullets());

        //Move one bandit to the roof of the train and shooting in both UP and DOWN direction:
        testSubject1.move(Direction.UP);

        assertEquals(2, testSubject1.getX());
        assertEquals(2, testSubject2.getX());
        assertEquals(0, testSubject1.getY());
        assertEquals(1, testSubject2.getY());

        testSubject1.shoot(Direction.DOWN);
        testSubject2.shoot(Direction.UP);

        assertEquals(1, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());
        assertEquals(2, testSubject1.getBullets());
        assertEquals(3, testSubject2.getBullets());

        //Move both bandits to the roof of the train and shooting UP:
        testSubject2.move(Direction.UP);

        assertEquals(2, testSubject1.getX());
        assertEquals(2, testSubject2.getX());
        assertEquals(0, testSubject1.getY());
        assertEquals(0, testSubject2.getY());

        testSubject1.shoot(Direction.UP);
        testSubject2.shoot(Direction.UP);

        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());
        assertEquals(1, testSubject1.getBullets());
        assertEquals(2, testSubject2.getBullets());

        testSubject1.shoot(Direction.UP);
        testSubject2.shoot(Direction.UP);
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());
        assertEquals(0, testSubject1.getBullets());
        assertEquals(1, testSubject2.getBullets());

        testSubject1.shoot(Direction.UP);
        testSubject2.shoot(Direction.UP);
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());
        assertEquals(0, testSubject1.getBullets());
        assertEquals(0, testSubject2.getBullets());


        testSubject1.shoot(Direction.UP);
        testSubject2.shoot(Direction.UP);
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(0, testSubject2.getBounty().size());
        assertEquals(0, testSubject1.getBullets());
        assertEquals(0, testSubject2.getBullets());
    }


    /**
     * Tests the move action for the bandit and other special situations that uses different actions.
     */
    @Test
    void doAction() {
        //Test the move actions
        Train testTrain = new Train();
        Bandit testSubject = new Bandit("bandit1", "Pierre", testTrain, 0, 0);
        testSubject.move(Direction.LEFT);
        assertEquals(0, testSubject.getX());
        assertEquals(0, testSubject.getY());

        testSubject.move(Direction.DOWN);
        testSubject.move(Direction.LEFT);
        assertEquals(0, testSubject.getX());
        assertEquals(1, testSubject.getY());

        testSubject.move(Direction.RIGHT);
        testSubject.move(Direction.RIGHT);
        testSubject.move(Direction.RIGHT);
        testSubject.move(Direction.RIGHT);
        testSubject.move(Direction.RIGHT);
        assertEquals(3, testSubject.getX());
        assertEquals(1, testSubject.getY());

        testSubject.move(Direction.DOWN);
        testSubject.move(Direction.DOWN);
        testSubject.move(Direction.DOWN);
        assertEquals(3, testSubject.getX());
        assertEquals(1, testSubject.getY());

        testSubject.move(Direction.UP);
        testSubject.move(Direction.UP);
        testSubject.move(Direction.UP);
        assertEquals(3, testSubject.getX());
        assertEquals(0, testSubject.getY());

        //Testing special situations with marshall, bounty, bandits, shooting and robbing:
        Train testTrain0 = new Train();
        Bandit testSubject1 = new Bandit("bandit1", "Manson", testTrain0, 0, 1);
        Bandit testSubject2 = new Bandit("bandit1", "Kemper", testTrain0, 2, 1);
        testTrain0.addEntity(testSubject1);
        testTrain0.addEntity(testSubject2);
        Marshall marshall= new Marshall(testTrain0,1);
        testTrain0.addEntity(marshall);
        // setting up all the loot in x=0 and x=2
        Bounty b1 = new Bounty("treasure", testTrain0, 0, 1000);
        Bounty b2 = new Bounty("bag", testTrain0, 2, 1000);
        Bounty b12 = new Bounty("bag", testTrain0, 0, 200);
        Bounty b22 = new Bounty("bag", testTrain0, 2, 100);
        Bounty b123 = new Bounty("jewel", testTrain0, 0, 500);
        Bounty b223 = new Bounty("jewel", testTrain0, 2, 500);
        testTrain0.addEntity(b1);
        testTrain0.addEntity(b2);
        testTrain0.addEntity(b12);
        testTrain0.addEntity(b22);
        testTrain0.addEntity(b123);
        testTrain0.addEntity(b223);

        testSubject1.doAction(Action.ROB);
        testSubject2.doAction(Action.ROB);
        assertEquals(1, testSubject1.getBounty().size());
        assertEquals(1, testSubject2.getBounty().size());

        marshall.move(Direction.LEFT);
        marshall.catchBandit();
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(1, testSubject2.getBounty().size());
        testSubject1.doAction(Action.ROB);
        testSubject2.doAction(Action.ROB);
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(2, testSubject2.getBounty().size());

        testSubject2.move(Direction.UP);
        testSubject1.addAction(Action.MOVE_DOWN);
        testSubject1.addAction(Action.ROB);
        testSubject2.addAction(Action.MOVE_DOWN);
        testSubject2.addAction(Action.ROB);

        testSubject1.move(Direction.DOWN);
        marshall.catchBandit();
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(2, testSubject2.getBounty().size());
        assertEquals(0, testSubject1.getActions().size());
        assertEquals(2, testSubject2.getActions().size());

        marshall.catchBandit();
        assertEquals(0, testSubject1.getBounty().size());
        assertEquals(2, testSubject2.getBounty().size());
        assertEquals(0, testSubject1.getActions().size());
        assertEquals(2, testSubject2.getActions().size());
    }
}