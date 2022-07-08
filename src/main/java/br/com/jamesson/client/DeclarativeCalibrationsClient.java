package br.com.jamesson.client;

import br.com.jamesson.dto.calibration.CalibrationParameters;
import br.com.jamesson.dto.calibration.CalibrationUpdateResult;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

@Client("http://localhost:8081")
public interface DeclarativeCalibrationsClient {

    @Get("/calibrations/{modelName}")
    //@Retryable(attempts = "3", delay = "2s")
    //@CircuitBreaker
    Mono<CalibrationParameters> getCalibrationParameters(String modelName);

    @Put("calibrations/updates")
    Mono<CalibrationUpdateResult> updateCalibrationParameters(@Body CalibrationParameters params);
}
