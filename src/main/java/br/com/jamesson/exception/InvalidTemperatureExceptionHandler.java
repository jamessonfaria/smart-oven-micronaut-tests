package br.com.jamesson.exception;

import br.com.jamesson.dto.ErrorMessage;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;


@Singleton
public class InvalidTemperatureExceptionHandler implements
        ExceptionHandler<InvalidTemperatureException, HttpResponse<ErrorMessage>> {

    @Override
    public HttpResponse<ErrorMessage> handle(HttpRequest request, InvalidTemperatureException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return HttpResponse.badRequest(errorMessage);
    }
}
