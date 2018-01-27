package hf.shopfounders.validation;

import hf.shopfounders.exception.BaseErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ParamNotNullValidator.class)
@Documented
public @interface ParamNotNull {
    String message() default BaseErrorCode.CODE1001;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}