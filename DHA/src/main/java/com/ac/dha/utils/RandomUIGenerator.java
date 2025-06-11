//package com.ac.dha.utils;
//
//import java.util.Random;
//
//public class RandomUIGenerator {
//	public static long generateNumericUI(int digits) {
//        if (digits <= 0) throw new IllegalArgumentException("Digits must be positive");
//        long min = (long) Math.pow(10, digits - 1);
//        long max = (long) Math.pow(10, digits) - 1;
//        Random random = new Random();
//        return min + random.nextLong() % (max - min + 1);
//    }
//}
