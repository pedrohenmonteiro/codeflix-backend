package com.pedromonteiro.domain.category;

import com.pedromonteiro.domain.validation.Error;
import com.pedromonteiro.domain.validation.ValidationHandler;
import com.pedromonteiro.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    public void checkNameConstraints() {

        final var name = this.category.getName();
        
        if(name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }
        if(name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }
        final int length = name.trim().length();
        if(length < 3 || length > 255) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255"));
        }
    }
    
}
