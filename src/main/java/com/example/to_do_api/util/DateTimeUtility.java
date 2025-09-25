package com.example.to_do_api.util;

import java.time.Instant;

public class DateTimeUtility {

    private static final long SECONDS_IN_A_DAY = 86400;

    public static long getCurrentTimeInSec() {
        return Instant.now().getEpochSecond();
    }

    public static long getEpochSecondsMinusDays(long days) {
        long durationInSeconds = days * SECONDS_IN_A_DAY;
        return Instant.now().getEpochSecond() - durationInSeconds;
    }

}