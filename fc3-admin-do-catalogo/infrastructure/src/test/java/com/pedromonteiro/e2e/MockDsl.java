package com.pedromonteiro.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.function.Function;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pedromonteiro.domain.Identifier;
import com.pedromonteiro.domain.category.CategoryID;
import com.pedromonteiro.infrastructure.category.models.CategoryResponse;
import com.pedromonteiro.infrastructure.category.models.CreateCategoryRequest;
import com.pedromonteiro.infrastructure.category.models.UpdateCategoryRequest;
import com.pedromonteiro.infrastructure.configuration.json.Json;

public interface MockDsl {

    MockMvc mvc();


    default ResultActions deleteACategory(final CategoryID anId) throws Exception {
        return this.delete("/categories/", anId);
    }

    default CategoryID givenACategory(final String aName, final String aDescription, final boolean isActive) throws Exception {
        final var aRequestBody = new CreateCategoryRequest(aName, aDescription, isActive);
        final var actualId = this.given("/categories", aRequestBody);
        return CategoryID.from(actualId);
    }

    default ResultActions listCategories(final int page, final int perPage) throws Exception {
        return listCategories(page, perPage, "", "", "");
    }

    default ResultActions listCategories(final int page, final int perPage, final String search) throws Exception {
        return listCategories(page, perPage, search, "", "");
    }

    default ResultActions listCategories(final int page, final int perPage, final String search, final String sort, final String direction) throws Exception {
        return this.list("/categories", page, perPage, search, sort, direction);
    }

    default CategoryResponse retrieveACategory(final CategoryID anId) throws Exception {
        return this.retrieve("/categories/", anId, CategoryResponse.class);
    }

    default ResultActions updateACategory(final CategoryID anId, final UpdateCategoryRequest aRequest) throws Exception {
        return this.update("/categories/", anId, aRequest);
    }


      default <A, D> List<D> mapTo(final List<A> actual, final Function<A, D> mapper) {
        return actual.stream()
                .map(mapper)
                .toList();
    }

    private String given(final String url, final Object body) throws Exception {
        final var aRequest = post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json.writeValueAsString(body));

        final var actualId = this.mvc().perform(aRequest)
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse().getHeader("Location")
                .replace("%s/".formatted(url), "");

        return actualId;
    }

    private ResultActions givenResult(final String url, final Object body) throws Exception {
        final var aRequest = post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json.writeValueAsString(body));

        return this.mvc().perform(aRequest);
    }

    private ResultActions list(final String url, final int page, final int perPage, final String search, final String sort, final String direction) throws Exception {
        final var aRequest = get(url)
                .queryParam("page", String.valueOf(page))
                .queryParam("perPage", String.valueOf(perPage))
                .queryParam("search", search)
                .queryParam("sort", sort)
                .queryParam("dir", direction)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        return this.mvc().perform(aRequest);
    }

    private <T> T retrieve(final String url, final Identifier anId, final Class<T> clazz) throws Exception {
        final var aRequest = get(url + anId.getValue())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        final var json = this.mvc().perform(aRequest)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        return Json.readValue(json, clazz);
    }

    private ResultActions retrieveResult(final String url, final Identifier anId) throws Exception {
        final var aRequest = get(url + anId.getValue())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        return this.mvc().perform(aRequest);
    }

    private ResultActions delete(final String url, final Identifier anId) throws Exception {
        final var aRequest = MockMvcRequestBuilders.delete(url + anId.getValue())
                .contentType(MediaType.APPLICATION_JSON);

        return this.mvc().perform(aRequest);
    }

    private ResultActions update(final String url, final Identifier anId, final Object aRequestBody) throws Exception {
        final var aRequest = put(url + anId.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json.writeValueAsString(aRequestBody));

        return this.mvc().perform(aRequest);
    }
}