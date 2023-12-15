package com.pedromonteiro.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.pedromonteiro.domain.exceptions.DomainException;
import com.pedromonteiro.domain.validation.handler.ThrowsValidationHandler;

public class CategoryTest {
    
    @Test
    void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = 
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);


       final var actualException = 
              Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

              Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
              Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        
    }
}
