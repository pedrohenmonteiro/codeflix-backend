package com.pedromonteiro.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedromonteiro.ControllerTest;
import com.pedromonteiro.application.category.create.CreateCategoryOutput;
import com.pedromonteiro.application.category.create.CreateCategoryUseCase;
import com.pedromonteiro.application.category.delete.DeleteCategoryUseCase;
import com.pedromonteiro.application.category.retrieve.get.CategoryOutput;
import com.pedromonteiro.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.pedromonteiro.application.category.retrieve.list.CategoryListOutput;
import com.pedromonteiro.application.category.retrieve.list.ListCategoriesUseCase;
import com.pedromonteiro.application.category.update.UpdateCategoryOutput;
import com.pedromonteiro.application.category.update.UpdateCategoryUseCase;
import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryID; 
import com.pedromonteiro.domain.exceptions.DomainException;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.validation.handler.Notification;
import com.pedromonteiro.infrastructure.api.CategoryAPI;
import com.pedromonteiro.infrastructure.category.models.CreateCategoryRequest;

@ControllerTest(controllers = CategoryAPI.class)
public class CategoryAPITest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CreateCategoryUseCase createCategoryUseCase;

    @MockBean
    private GetCategoryByIdUseCase getCategoryByIdUseCase;

    @MockBean
    private UpdateCategoryUseCase updateCategoryUseCase;

    @MockBean
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @MockBean
    private ListCategoriesUseCase listCategoriesUseCase;

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() throws Exception {
        // given
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aInput =
                new CreateCategoryRequest(expectedName, expectedDescription, expectedIsActive);

        when(createCategoryUseCase.execute(any()))
                .thenReturn(Right(CreateCategoryOutput.from("123")));

        // when
        final var request = post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(aInput));

        final var response = this.mvc.perform(request)
                .andDo(print());

        // then
        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/categories/123"))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo("123")));

        verify(createCategoryUseCase, times(1)).execute(argThat(cmd ->
                Objects.equals(expectedName, cmd.name())
                        && Objects.equals(expectedDescription, cmd.description())
                        && Objects.equals(expectedIsActive, cmd.isActive())
        ));
    }

   
}