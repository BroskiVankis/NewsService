package com.example.demo.model.dto.news.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Custom annotation, which can be applied to other annotations.

@Retention(RetentionPolicy.RUNTIME) // -> retention policy -> Will be available at Runtime
@Target(ElementType.TYPE) // ->specifies where can be applied -> can be applied to classes or interfaces
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

    String first();

    String second();

    String message() default "Invalid Email"; // if validation fails

    Class<?> [] groups() default {}; //allows annotation to be associated with validation groups

    Class<? extends Payload> [] payload() default {}; // Allows annotation to carry payload info

}
