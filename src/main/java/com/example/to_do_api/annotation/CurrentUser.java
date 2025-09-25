package com.example.to_do_api.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * Custom annotation to inject the currently authenticated user
 * into controller method parameters.
 * Combines {@link AuthenticationPrincipal} for easier usage.
 */

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}