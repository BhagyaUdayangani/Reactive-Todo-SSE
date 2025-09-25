package com.example.to_do_api.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Since annotations themselves do not contain logic,
 *  the test verifies that the annotation is present
 *  and has the correct meta-annotations
 */

public class CurrentUserTest {

    @Test
    void annotationHasAuthenticationPrincipal() {
        Annotation[] annotations = CurrentUser.class.getAnnotations();
        boolean found = false;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(AuthenticationPrincipal.class)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "@CurrentUser should be meta-annotated with @AuthenticationPrincipal");
    }

    @Test
    void annotationRetentionIsRuntime() {
        Retention retention = CurrentUser.class.getAnnotation(Retention.class);
        assertNotNull(retention);
        assertEquals(RetentionPolicy.RUNTIME, retention.value());
    }

    @Test
    void annotationTargetIsParameterOrType() {
        Target target = CurrentUser.class.getAnnotation(Target.class);
        assertNotNull(target);
        assertArrayEquals(new ElementType[]{ElementType.PARAMETER, ElementType.TYPE}, target.value());
    }
}
