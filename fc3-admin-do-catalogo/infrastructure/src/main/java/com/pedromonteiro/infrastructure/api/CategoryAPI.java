package com.pedromonteiro.infrastructure.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.infrastructure.category.models.CategoryResponse;
import com.pedromonteiro.infrastructure.category.models.CreateCategoryRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/categories")
@Tag(name = "Categories")
public interface CategoryAPI {
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
        )
    @Operation(
        summary = "Create a new category",
        responses = {
            @ApiResponse(description = "Created successfully", responseCode = "201"),
            @ApiResponse(description = "Validaiton error was thrown", responseCode = "422"),
            @ApiResponse(description = "Internal server error", responseCode = "500")
        }
    )
    ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest input);

    @GetMapping
    @Operation(
        summary = "List all categories paginated",
        responses = {
            @ApiResponse(description = "Listed successfully", responseCode = "200"),
            @ApiResponse(description = "Invalid parameter received", responseCode = "422"),
            @ApiResponse(description = "Internal server error", responseCode = "500")
        }
    )
    Pagination<?> listCategories(
        @RequestParam(name = "search", required = false, defaultValue = "") final String search,
        @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
        @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
        @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
        @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
);

        @GetMapping(value = "{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
        )
        @ApiResponses(value = {
            @ApiResponse(description = "Category retrieved succesfully", responseCode = "200"),
            @ApiResponse(description = "Category was not found", responseCode = "404"),
            @ApiResponse(description = "Internal server error", responseCode = "500")
        })
        CategoryResponse getById(@PathVariable String id);
}

   