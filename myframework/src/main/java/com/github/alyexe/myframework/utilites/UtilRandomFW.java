package com.github.alyexe.myframework.utilites;

import java.util.Random;

public class UtilRandomFW {
    public static int getCasualNumber(int number) {
        Random random = new Random();
        int casualNumber = random.nextInt(number);
        return casualNumber;
    }
}
