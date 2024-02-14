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

    @Test
    void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = "   ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = 
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);


       final var actualException = 
              assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

              assertEquals(expectedErrorCount, actualException.getErrors().size());
              assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        
    }

    @Test
    void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = "Lu ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = 
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);


       final var actualException = 
              assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

              assertEquals(expectedErrorCount, actualException.getErrors().size());
              assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        
    }

    @Test
    void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = """
            O sol brilhava intensamente no céu azul. As árvores balançavam suavemente ao vento.
            O pássaro cantava alegremente em seu ninho.
            O aroma das flores inundava o ar.
            Tudo parecia perfeito naquela tarde tranquila de primavera.
            O sol brilhava intensamente no céu azul. As árvores balançavam suavemente ao vento.
           
                """;;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = 
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);


       final var actualException = 
              assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

              assertEquals(expectedErrorCount, actualException.getErrors().size());
              assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldNotReceiveAnError() {
        final var expectedName = "Filmes";
        final var expectedDescription = "   ";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertNotNull(actualCategory.getCreatedAt());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());

    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldNotReceiveAnError() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNotNull(actualCategory.getDeletedAt());

    }
  
}
