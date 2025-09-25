package com.example.to_do_api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuditTest {

    @Test
    void testGettersAndSetters() {
        Audit audit = new Audit();
        audit.setStatus(true);
        audit.setCreatedAt(123L);
        audit.setUpdatedAt(456L);
        audit.setCreatedBy("user1");
        audit.setUpdatedBy("user2");

        assertTrue(audit.isStatus());
        assertEquals(123L, audit.getCreatedAt());
        assertEquals(456L, audit.getUpdatedAt());
        assertEquals("user1", audit.getCreatedBy());
        assertEquals("user2", audit.getUpdatedBy());
    }

    @Test
    void testEqualsAndHashCode() {
        Audit a1 = new Audit();
        Audit a2 = new Audit();
        a1.setStatus(true);
        a2.setStatus(true);
        a1.setCreatedAt(1L);
        a2.setCreatedAt(1L);
        a1.setUpdatedAt(2L);
        a2.setUpdatedAt(2L);
        a1.setCreatedBy("u");
        a2.setCreatedBy("u");
        a1.setUpdatedBy("v");
        a2.setUpdatedBy("v");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    void testToString() {
        Audit audit = new Audit();
        audit.setStatus(false);
        audit.setCreatedAt(10L);
        audit.setUpdatedAt(20L);
        audit.setCreatedBy("a");
        audit.setUpdatedBy("b");

        String str = audit.toString();
        assertTrue(str.contains("status=false"));
        assertTrue(str.contains("createdAt=10"));
        assertTrue(str.contains("updatedAt=20"));
        assertTrue(str.contains("createdBy=a"));
        assertTrue(str.contains("updatedBy=b"));
    }

}
