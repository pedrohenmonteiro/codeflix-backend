package com.pedromonteiro.application.category.update;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryGateway;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {
    
    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId() {
        final var aCategory = Category.newCategory("FILM", null, true);

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var expectedId = aCategory.getId();
        
        final var aCommand = UpdateCategoryCommand.with(
            expectedId.getValue(),
            expectedName,
            expectedDescription,
            expectedIsActive
        );

        Mockito.when(categoryGateway.findById(Mockito.eq(expectedId)))
            .thenReturn(Optional.of(aCategory));

        Mockito.when(categoryGateway.update(Mockito.any()))
            .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        assertNotNull(actualOutput);
        assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1)).update(Mockito.argThat(aUpdatedCategory ->

        ));

    }
}
