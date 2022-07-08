package br.com.jamesson;

import br.com.jamesson.dto.Recipe;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipesControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    @Order(3)
    @DisplayName("Shoud GET recipes sucessfully")
    void shoudGetRecipesSuccessfully(){
        // given
        HttpRequest request = HttpRequest.GET("/recipes")
                .header("Accept-Version", "1");

        // when
        HttpResponse<List<Recipe>> response = client.toBlocking()
                .exchange(request, Argument.of(List.class, Recipe.class));

        // then
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(4, response.body().size());
    }

    @Test
    @Order(2)
    @DisplayName("Should POST recipes successfully")
    void shouldPOSTRecipeSucessfully() {
        // given
        Recipe lasagna = new Recipe("Lasagna", 450, 1600);
        HttpRequest request = HttpRequest.POST("/recipes", lasagna);

        // when
        HttpResponse<Recipe> response = client.toBlocking().exchange(request, Recipe.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(lasagna.getName(), response.body().getName());
    }

    @Test
    @Order(1)
    @DisplayName("Should return error on invalid temp")
    void shouldReturnErrorOnInvalidTemp() {
        // given
        Recipe lasagna = new Recipe("Lasagna", 1000, 1600);
        HttpRequest request = HttpRequest.POST("/recipes", lasagna);

        // when
        HttpClientResponseException exception = assertThrows(HttpClientResponseException.class,
                () -> client.toBlocking().exchange(request, Recipe.class));

        // then
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}
