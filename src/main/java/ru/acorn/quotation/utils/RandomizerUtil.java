package ru.acorn.quotation.utils;

import java.util.Random;

public class RandomizerUtil {
    public static Long getRandomNumberUsingId() {
        Random random = new Random();
        return random.nextLong();
    }
}
