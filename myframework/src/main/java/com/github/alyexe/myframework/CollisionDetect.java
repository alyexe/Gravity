package com.github.alyexe.myframework;

public class CollisionDetect {

    public static boolean isCollisionDetected(ObjectFW object1, ObjectFW object2) {
        double object1x = object1.getHitBox().centerX();
        double object1y = object1.getHitBox().centerY();

        double object2x = object2.getHitBox().centerX();
        double object2y = object2.getHitBox().centerY();

        double objectRadius1 = object1.getRadius();
        double objectRadius2 = object2.getRadius();

        double dX = object1x - object2x;
        double dY = object1y - object2y;

        double objectsDistance = Math.sqrt(dX * dX + dY * dY);

        return objectsDistance < (objectRadius1 + objectRadius2);
    }
}
