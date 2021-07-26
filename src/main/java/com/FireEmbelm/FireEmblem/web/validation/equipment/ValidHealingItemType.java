package com.FireEmbelm.FireEmblem.web.validation.equipment;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidHealingItemTypeImpl.class)
public @interface ValidHealingItemType {
    String message() default "Invalid healing item type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
