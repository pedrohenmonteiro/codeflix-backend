package com.pedromonteiro.domain.category;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.pedromonteiro.domain.exceptions.DomainException;
import com.pedromonteiro.domain.validation.handler.ThrowsValidationHandler;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertNotNull(actualCategory.getCreatedAt());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());

    }
    
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
              assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

              assertEquals(expectedErrorCount, actualException.getErrors().size());
              assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        
    }
}
