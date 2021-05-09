package engine.gameElements;

import engine.utils.Direction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BanditTest {

    //Used to test the rob and dropBounty actions
    @org.junit.jupiter.api.Test
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
        testSubject.rob();
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

    }

    //Used to test the shoot action
    @org.junit.jupiter.api.Test
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

    //Used to test the dropBounty action
    @org.junit.jupiter.api.Test
    void dropBounty() {
    }

    //Used to test the move action
    @org.junit.jupiter.api.Test
    void doAction() {
    }
}