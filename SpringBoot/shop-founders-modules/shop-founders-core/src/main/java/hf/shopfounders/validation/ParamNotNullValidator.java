package hf.shopfounders.validation;


import hf.shopfounders.exception.BaseErrorCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ParamNotNullValidator implements ConstraintValidator<ParamNotNull, String> {

    @Override
    public void initialize(ParamNotNull paramNotNull) {
    }

    @Override
    public boolean isValid(String parameter, ConstraintValidatorContext context) {
        if(parameter == null
                || parameter.replaceAll(" ", "").isEmpty()
                || parameter.equals("null")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(BaseErrorCode.CODE1000)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
