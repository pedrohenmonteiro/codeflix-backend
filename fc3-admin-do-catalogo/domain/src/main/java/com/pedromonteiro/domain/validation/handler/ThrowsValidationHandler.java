package com.pedromonteiro.domain.validation.handler;

import java.util.List;

import com.pedromonteiro.domain.exceptions.DomainException;
import com.pedromonteiro.domain.validation.Error;
import com.pedromonteiro.domain.validation.ValidationHandler;

public class ThrowsValidationHandler implements ValidationHandler{

    @Override
    public ValidationHandler append(Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler aHandler) {
        throw DomainException.with(aHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {

        try {
            aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
       return List.of();
    }
    
}
