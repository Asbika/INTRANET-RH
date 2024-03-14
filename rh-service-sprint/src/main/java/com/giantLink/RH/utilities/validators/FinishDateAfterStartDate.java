package com.giantLink.RH.utilities.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FinishDateAfterStartDateValidator.class)
public @interface FinishDateAfterStartDate
{
    String message() default "Finish date must be after start date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
