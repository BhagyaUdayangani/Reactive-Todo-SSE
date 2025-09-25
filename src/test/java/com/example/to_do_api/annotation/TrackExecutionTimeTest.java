package com.example.to_do_api.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import static org.junit.jupiter.api.Assertions.*;

public class TrackExecutionTimeTest {

    @Test
    void annotationRetentionIsRuntime() {
        Retention retention = TrackExecutionTime.class.getAnnotation(Retention.class);
        assertNotNull(retention);
        assertEquals(RetentionPolicy.RUNTIME, retention.value());
    }

    @Test
    void annotationTargetIsMethod() {
        Target target = TrackExecutionTime.class.getAnnotation(Target.class);
        assertNotNull(target);
        assertArrayEquals(new ElementType[]{ElementType.METHOD}, target.value());
    }
}
