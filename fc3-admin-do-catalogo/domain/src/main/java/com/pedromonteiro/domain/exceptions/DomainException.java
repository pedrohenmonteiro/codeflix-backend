package com.pedromonteiro.domain.exceptions;

import java.util.List;

import com.pedromonteiro.domain.validation.Error;

public class DomainException extends RuntimeException {
    
    private final List<Error> errors;

    private DomainException(final List<Error> anErrors) {
        super("", null, true, false);
        this.errors = anErrors;
    }


    public static DomainException with(final List<Error> anErrors) {
        return new DomainException(anErrors);
    }


    public List<Error> getErrors() {
        return errors;
    }
    
    
}
