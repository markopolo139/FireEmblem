package com.FireEmbelm.FireEmblem.web.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidStatTypeImpl.class)
public @interface ValidStatType {
    String message() default "Invalid stat type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
