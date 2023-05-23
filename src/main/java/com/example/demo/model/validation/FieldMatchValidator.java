package com.example.demo.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;


// A class to validate the constraints defined by "FieldMatch" annotation
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String first;

    private String second;

    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotations) {
        this.first = constraintAnnotations.first();
        this.second = constraintAnnotations.second();
        this.message = constraintAnnotations.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //BeanWrapper used to access and manipulate properties of value object
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        // Getting the values of first and second properties using BeanWrapper
        Object firstValue = beanWrapper.getPropertyValue(this.first);
        Object secondValue = beanWrapper.getPropertyValue(this.second);
        boolean valid; // storing the result of the validation

        // Validation logic
        if(firstValue == null) {
            valid = secondValue == null;
        } else {
            valid = firstValue.equals(secondValue);
        }

        // if validation fails, adding a constraint violation to context
        if(!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(second)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
