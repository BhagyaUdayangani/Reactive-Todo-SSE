package com.example.to_do_api.model;

import com.example.to_do_api.enums.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskEventTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        Task task = new Task();
        TaskEvent event = new TaskEvent(TaskType.CREATED, task);

        assertEquals(TaskType.CREATED, event.getType());
        assertEquals(task, event.getTask());
    }

    @Test
    void testSetters() {
        Task task1 = new Task();
        Task task2 = new Task();
        TaskEvent event = new TaskEvent(TaskType.CREATED, task1);

        event.setType(TaskType.UPDATED);
        event.setTask(task2);

        assertEquals(TaskType.UPDATED, event.getType());
        assertEquals(task2, event.getTask());
    }

    @Test
    void testEqualsAndHashCode() {
        Task task = new Task();
        TaskEvent e1 = new TaskEvent(TaskType.CREATED, task);
        TaskEvent e2 = new TaskEvent(TaskType.CREATED, task);

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void testToString() {
        Task task = new Task();
        TaskEvent event = new TaskEvent(TaskType.CREATED, task);

        String str = event.toString();
        assertTrue(str.contains("type=CREATED"));
        assertTrue(str.contains("task="));
    }

}
