package br.com.jamesson.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class NotFoundController {

    @Get("/404")
    public HttpResponse notFoundController() {
        return HttpResponse.notFound();
    }

}
