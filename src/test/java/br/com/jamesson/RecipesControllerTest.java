package br.com.jamesson;

import br.com.jamesson.dto.Recipe;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class RecipesControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    @DisplayName("Shoud GET recipes sucessfully")
    void shoudGetRecipesSuccessfully(){
        // given
        HttpRequest request = HttpRequest.GET("/recipes")
                .header("Accept-Version", "1");

        // when
        HttpResponse response = client.toBlocking()
                .exchange(request, Argument.of(List.class, Recipe.class));

        // then
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(3, ((ArrayList) response.body()).size());
    }


}
