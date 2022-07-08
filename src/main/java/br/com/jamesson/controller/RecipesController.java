package br.com.jamesson.controller;

import br.com.jamesson.dto.Recipe;
import br.com.jamesson.dto.RecipeV2;
import br.com.jamesson.service.RecipesService;
import com.fasterxml.jackson.core.JsonParseException;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.*;
import io.micronaut.http.hateoas.JsonError;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Controller("recipes")
public class RecipesController {

    private final RecipesService service;

    @Inject
    public RecipesController(RecipesService service) {
        this.service = service;
    }

    @Get
    @Version("1")
    public List<Recipe> getRecipes(@QueryValue Optional<String> query){
        return service.getRecipes(query);
    }

    @Get
    @Version("2")
    public List<RecipeV2> getRecipesV2(@QueryValue Optional<String> query){
        return service.getRecipesV2(query);
    }

    @Post
    public Recipe createRecipe(@Body Recipe recipe){
        return service.createRecipe(recipe);
    }

    @Put("/{recipeName}")
    public Recipe updateRecipe(String recipeName, Recipe recipe){
        return service.updateRecipe(recipeName, recipe);
    }

    @Delete("/{recipeName}")
    public Recipe deleteRecipe(String recipeName){
        return service.deleteRecipe(recipeName);
    }

    @Error // - tratamento de erros local, apenas para esse controller
    public HttpResponse<JsonError> jsonError(HttpRequest request, JsonParseException e){
        JsonError error = new JsonError("Invalid JSON: " + e.getMessage());
        return HttpResponse.badRequest(error);
    }

    @Error(global = true, status = HttpStatus.NOT_FOUND) // - tratamento de erros global, para qualquer controler que tiver NOT FOUND
    public HttpResponse<JsonError> notFound(HttpRequest request){
        JsonError error = new JsonError("Something went wrong trying to find the resource");
        return HttpResponse.notFound().body(error);
    }

}
