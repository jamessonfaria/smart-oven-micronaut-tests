package br.com.jamesson.dto.calibration;

import java.time.LocalDate;

public class CalibrationStatus {
    private String model;
    private String serial;
    private LocalDate data;

    public CalibrationStatus() {
    }

    public CalibrationStatus(String model, String serial, LocalDate data) {
        this.model = model;
        this.serial = serial;
        this.data = data;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
