package com.pedromonteiro.domain.exceptions;

import java.util.List;

import com.pedromonteiro.domain.validation.Error;

public class DomainException extends NoStackTraceException {
    
    private final List<Error> errors;

    private DomainException(final String aMessage, final List<Error> anErrors) {
        super(aMessage);
        this.errors = anErrors;
    }


    public static DomainException with(final Error anError) {
        return new DomainException(anError.message() , List.of(anError));
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }


    public List<Error> getErrors() {
        return errors;
    }
    
    
}
