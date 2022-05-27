package com.office.home.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RUNTIME)
@Target(METHOD)
@PreAuthorize("has role('ROLE_ADMIN')")
public @interface IsAdmin {

}
