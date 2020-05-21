package com.github.alyexe.myframework;

public class CollisionDetect {
    static double object1x;
    static double object1y;

    static double object2x;
    static double object2y;

    static double objectRadius1;
    static double objectRadius2;

    static double dX;
    static double dY;

    static double objectsDistance;

    public static boolean isCollisionDetected(ObjectFW object1, ObjectFW object2) {
        object1x = object1.getHitBox().centerX();
        object1y = object1.getHitBox().centerY();

        object2x = object2.getHitBox().centerX();
        object2y = object2.getHitBox().centerY();

        objectRadius1 = object1.getRadius();
        objectRadius2 = object2.getRadius();

        dX = object1x - object2x;
        dY = object1y - object2y;

        objectsDistance = Math.sqrt(dX * dX + dY * dY);

        if (objectsDistance < (objectRadius1 + objectRadius2)) return true;
        return false;
    }
}
