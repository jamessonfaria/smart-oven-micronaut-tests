package br.com.jamesson.service;

import br.com.jamesson.client.DeclarativeCalibrationsClient;
import br.com.jamesson.client.LowLevelCalibrationsClient;
import br.com.jamesson.dto.calibration.CalibrationParameters;
import br.com.jamesson.dto.calibration.CalibrationStatus;
import br.com.jamesson.dto.calibration.CalibrationUpdateResult;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Singleton
public class CalibrationService {

    private static final Logger logger = LoggerFactory.getLogger(CalibrationService.class);
    private static final String OVEN_MODEL = "PLURAL_OVEN";
    private static final String SERIAL_NO = "123456";

    private final LowLevelCalibrationsClient lowLevelClient;
    private final DeclarativeCalibrationsClient declarativeClient;

    public CalibrationService(LowLevelCalibrationsClient lowLevelClient, DeclarativeCalibrationsClient declarativeClient) {
        this.lowLevelClient = lowLevelClient;
        this.declarativeClient = declarativeClient;
    }

    public Mono<CalibrationUpdateResult> calibrateOven(){
        return lowLevelClient.getCalibrationParameters(OVEN_MODEL)
                .doOnNext(parameters -> logger.info("Calibrations parameters received {}", parameters))
                .doOnNext(parameters -> logger.info("Calibrations oven ..."))
                .doOnNext(parameters -> logger.info("Oven calibration complete ! Sending updates to cloud..."))
                .flatMap(parameters -> lowLevelClient.updateCalibrationStatus(new CalibrationStatus(OVEN_MODEL, SERIAL_NO, LocalDate.now()))
                .doOnNext(updateResult -> logger.info("Update result received {}", updateResult)));
    }

    public Mono<CalibrationUpdateResult> calibrateOvenUsingDeclarativeClient() {
        logger.info("Calibrating oven using declarative client ...");
        return declarativeClient.getCalibrationParameters(OVEN_MODEL)
                .doOnNext(parameters -> logger.info("Calibrations parameters received {}", parameters))
                .doOnNext(parameters -> logger.info("Calibrations oven ..."))
                .doOnNext(parameters -> logger.info("Oven calibration complete ! Sending updates to cloud..."))
                .flatMap(parameters -> declarativeClient.updateCalibrationParameters(new CalibrationParameters(OVEN_MODEL, 100, 100))
                        .doOnNext(updateResult -> logger.info("Update result received {}", updateResult)));
    }
}
