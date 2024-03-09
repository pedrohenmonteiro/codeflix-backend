package com.pedromonteiro.domain.validation.handler;

import java.util.ArrayList;
import java.util.List;

import com.pedromonteiro.domain.exceptions.DomainException;
import com.pedromonteiro.domain.validation.Error;
import com.pedromonteiro.domain.validation.ValidationHandler;

public class Notification implements ValidationHandler{

    private final List<Error> errors;

    private Notification(List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Error anError) {
        return new Notification(new ArrayList<>()).append(anError);
    }

    public static Notification create(final Throwable t) {
        return create(new Error(t.getMessage()));
    }

    @Override
    public Notification append(Error anError) {
        this.errors.add(anError);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler aHandler) {
        this.errors.addAll(aHandler.getErrors());
        return this;
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {

        try {
            return aValidation.validate();
        } catch (final DomainException ex) {
            this.errors.addAll(ex.getErrors());
        } catch (final Throwable t) {
            this.errors.add(new Error(t.getMessage()));
        }

        return null;
    }

    @Override
    public List<Error> getErrors() {
       return this.errors;
    }
    
}
