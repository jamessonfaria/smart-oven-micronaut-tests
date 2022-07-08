package br.com.jamesson.client;

import br.com.jamesson.dto.calibration.CalibrationParameters;
import br.com.jamesson.dto.calibration.CalibrationStatus;
import br.com.jamesson.dto.calibration.CalibrationUpdateResult;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Singleton
public class LowLevelCalibrationsClient {

    private static final Logger logger = LoggerFactory.getLogger(LowLevelCalibrationsClient.class);

    private HttpClient client;

    public LowLevelCalibrationsClient(@Client("http://localhost:8081") HttpClient client){
        this.client = client;
    }

    public Mono<CalibrationParameters> getCalibrationParameters(String modelName) {
        logger.info("Calling GET calibration parameters using the low-level client for model {}", modelName);

        Mono<CalibrationParameters> calibrationParameters =
                Mono.from(client.retrieve(HttpRequest.GET("/calibrations/" + modelName), CalibrationParameters.class));

        return calibrationParameters;
    }

    public Mono<CalibrationUpdateResult> updateCalibrationStatus(CalibrationStatus calibrationStatus) {
        logger.info("Calling PUT calibration update using the low-level client with {}", calibrationStatus);

        return Mono.from(client.retrieve(HttpRequest.PUT("/calibrations/updates", calibrationStatus),
                CalibrationUpdateResult.class));
    }


}
