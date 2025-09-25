package com.example.to_do_api.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for enums typically verify their values,
 * names, and any custom methods. For your TaskType enum,
 * a simple test can check all constants are present and
 * their names are correct.
 */

public class TaskTypeTest {

    @Test
    void allEnumValuesPresent() {
        TaskType[] values = TaskType.values();
        assertArrayEquals(new TaskType[]{TaskType.CREATED, TaskType.UPDATED, TaskType.DELETED}, values);
    }

    @Test
    void valueOfReturnsCorrectEnum() {
        assertEquals(TaskType.CREATED, TaskType.valueOf("CREATED"));
        assertEquals(TaskType.UPDATED, TaskType.valueOf("UPDATED"));
        assertEquals(TaskType.DELETED, TaskType.valueOf("DELETED"));
    }
}
