package br.com.jamesson;

import br.com.jamesson.client.DeclarativeCalibrationsClient;
import br.com.jamesson.dto.calibration.CalibrationParameters;
import br.com.jamesson.dto.calibration.CalibrationUpdateResult;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@MicronautTest
public class CallibratoinsControllerTest {

    @MockBean(DeclarativeCalibrationsClient.class)
    DeclarativeCalibrationsClient calibrationsClient(){
        return mock(DeclarativeCalibrationsClient.class);
    }

    @Inject
    DeclarativeCalibrationsClient calibrationsClient;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    @DisplayName("Should calibrate oven successfully")
    void shouldCalibrateOvenSuccessfully(){
        // given
        CalibrationParameters calibrationParameters = new CalibrationParameters("Model", 100, 500);
        when(calibrationsClient.getCalibrationParameters(any())).thenReturn(Mono.just(calibrationParameters));

        CalibrationUpdateResult calibrationUpdateResult = new CalibrationUpdateResult(true);
        when(calibrationsClient.updateCalibrationParameters(any())).thenReturn(Mono.just(calibrationUpdateResult));

        MutableHttpRequest request = HttpRequest.GET("/calibrations/calibrate/declarative");

        // when
        HttpResponse<CalibrationUpdateResult> response = client.toBlocking().exchange(request, CalibrationUpdateResult.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatus());
        assertTrue(response.body().getSuccess());
    }

}
