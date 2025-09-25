package com.example.to_do_api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeUtilityTest {

    @Test
    void testGetCurrentTimeInSec() {
        long now = System.currentTimeMillis() / 1000;
        long utilNow = DateTimeUtility.getCurrentTimeInSec();
        // Allow a small difference due to execution time
        assertTrue(Math.abs(utilNow - now) < 2);
    }

    @Test
    void testGetEpochSecondsMinusDays() {
        long days = 2;
        long now = DateTimeUtility.getCurrentTimeInSec();
        long expected = now - days * 86400;
        long actual = DateTimeUtility.getEpochSecondsMinusDays(days);
        // Allow a small difference due to execution time
        assertTrue(Math.abs(actual - expected) < 2);
    }

}
