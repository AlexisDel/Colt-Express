package engine.gameElements;

import engine.utils.Direction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Unit Test for all 3 Bandit actions and their complementary methods
class BanditTest {

    //Used to test the rob  action
    @Test
    void rob() {
        Train testTrain= new Train();
        Bandit testSubject= new Bandit("bandit1", "Pierre", testTrain,0,1 );
        Bounty b1= new Bounty("test", testTrain, 0, 1000);
        Bounty b2= new Bounty("test", testTrain, 0, 1000);

        testTrain.addEntity(testSubject);
        testTrain.addEntity(b1);
        testTrain.addEntity(b2);

        assertEquals(0,testSubject.getBounty().size());

        testSubject.rob();
        assertEquals(1,testSubject.getBounty().size());

        testSubject.rob();
        assertEquals(2,testSubject.getBounty().size());

        testTrain.addEntity(b1);
        testTrain.addEntity(b2);
        testSubject.rob();
        testSubject.rob();
        assertEquals(4,testSubject.getBounty().size());

        testSubject.dropBounty();
        testSubject.dropBounty();
        testSubject.dropBounty();
        assertEquals(1,testSubject.getBounty().size());

        testSubject.dropBounty();
        assertEquals(0,testSubject.getBounty().size());

        testSubject.dropBounty();
        assertEquals(0,testSubject.getBounty().size());

        //Scenario: Bandits move and then steal
        Train testTrain2= new Train();
        Bandit testSubject1= new Bandit("bandit1", "Pierre", testTrain2,0,0 );
        Bandit testSubject2= new Bandit("bandit2", "Kemper", testTrain2,2,0);

        Bounty b12= new Bounty("test", testTrain2, 0, 1000);
        Bounty b22= new Bounty("test", testTrain2, 2, 1000);

        testTrain2.addEntity(testSubject1);
        testTrain2.addEntity(testSubject2);

        testTrain2.addEntity(b12);
        testTrain2.addEntity(b22);

        testSubject1.move(Direction.DOWN);
        testSubject2.move(Direction.DOWN);
        testSubject1.rob();
        testSubject2.rob();

        assertEquals(1,testSubject1.getBounty().size());
        assertEquals(1,testSubject2.getBounty().size());

        Train testTrain3= new Train();
        Bandit testSubject3= new Bandit("bandit1", "Pierre", testTrain3,2,0 );
        Bandit testSubject4= new Bandit("bandit2", "Kemper", testTrain3,2,0);

        Bounty b123= new Bounty("test", testTrain3, 2, 500);
        Bounty b223= new Bounty("test", testTrain3, 2, 1000);

        testTrain3.addEntity(testSubject3);
        testTrain3.addEntity(testSubject4);

        testTrain3.addEntity(b123);
        testTrain3.addEntity(b223);

        testSubject3.move(Direction.DOWN);
        testSubject4.move(Direction.DOWN);
        testSubject3.rob();
        testSubject4.rob();

        assertEquals(1,testSubject3.getBounty().size());
        assertEquals(1,testSubject4.getBounty().size());
        assertTrue(testSubject3.getMoney()==500 ||testSubject4.getMoney()==1000);
        assertTrue(testSubject4.getMoney()==500 ||testSubject4.getMoney()==1000);
        testSubject3.dropBounty();
        testSubject4.dropBounty();
        assertEquals(0,testSubject3.getBounty().size());
        assertEquals(0,testSubject4.getBounty().size());
        assertEquals(0,testSubject3.getMoney());
        assertEquals(0,testSubject4.getMoney());




    }

    //Used to test the shoot action
    @Test
    void shoot() {
        Train testTrain= new Train();
        Bandit testSubject1= new Bandit("bandit1", "Manson", testTrain,0,1 );
        Bandit testSubject2= new Bandit("bandit1", "Kemper", testTrain,2,1 );
        assertEquals(0,testSubject1.getBounty().size());
        assertEquals(0,testSubject2.getBounty().size());
        assertEquals(5,testSubject1.getBullets());
        assertEquals(5,testSubject2.getBullets());

        testSubject1.shoot(Direction.RIGHT);
        assertEquals(0,testSubject1.getBounty().size());
        assertEquals(0,testSubject2.getBounty().size());
        assertEquals(4,testSubject1.getBullets());
        assertEquals(5,testSubject2.getBullets());

        testSubject2.shoot(Direction.LEFT);
        assertEquals(0,testSubject1.getBounty().size());
        assertEquals(0,testSubject2.getBounty().size());
        assertEquals(4,testSubject1.getBullets());
        assertEquals(4,testSubject2.getBullets());



        Bounty b1= new Bounty("test", testTrain, 0, 1000);
        Bounty b2= new Bounty("test", testTrain, 0, 1000);

    }


    //Used to test the move action
    @Test
    void doAction() {
    }
}