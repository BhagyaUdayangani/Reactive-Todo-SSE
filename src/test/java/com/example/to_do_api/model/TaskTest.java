package com.example.to_do_api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testGettersAndSetters() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setCompleted(false);
        task.setCreatedBy("user1");
        task.setCreatedAt(123L);
        task.setUpdatedAt(456L);
        task.setUpdatedBy("user2");
        task.setStatus(true);

        assertEquals(1L, task.getId());
        assertEquals("Test Task", task.getTitle());
        assertFalse(task.isCompleted());
        assertTrue(task.isStatus());
        assertEquals(123L, task.getCreatedAt());
        assertEquals(456L, task.getUpdatedAt());
        assertEquals("user1", task.getCreatedBy());
        assertEquals("user2", task.getUpdatedBy());
    }

    @Test
    void testEqualsAndHashCode() {
        Task t1 = new Task();
        Task t2 = new Task();

        t1.setId(1L);
        t1.setTitle("Test Task");
        t1.setCompleted(false);
        t1.setCreatedBy("user1");
        t1.setCreatedAt(123L);
        t1.setUpdatedAt(456L);
        t1.setUpdatedBy("user2");
        t1.setStatus(true);

        t2.setId(1L);
        t2.setTitle("Test Task");
        t2.setCompleted(false);
        t2.setCreatedBy("user1");
        t2.setCreatedAt(123L);
        t2.setUpdatedAt(456L);
        t2.setUpdatedBy("user2");
        t2.setStatus(true);

        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void testToString() {
        Task task = new Task();
        String str = task.toString();
        assertNotNull(str);
    }

}
