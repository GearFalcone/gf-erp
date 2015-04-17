package org.gearfalcone.erp.db.validation.annotations;

import org.gearfalcone.erp.db.validation.validators.UniqueLoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by andy on 16.04.15.
 */

@Constraint(validatedBy={UniqueLoginValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueLogin {
    public abstract String message() default "must be unique";
    public abstract Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};

}

