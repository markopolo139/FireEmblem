package com.FireEmbelm.FireEmblem.web.validation.shop;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEquipmentImpl.class)
public @interface ValidEquipment {
    String message() default "Invalid equipment type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
