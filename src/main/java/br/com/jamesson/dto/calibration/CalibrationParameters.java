package br.com.jamesson.dto.calibration;

public class CalibrationParameters {

    private String modelName;
    private Integer maxTemp;
    private Integer minTemp;

    public CalibrationParameters() {
    }

    public CalibrationParameters(String modelName, Integer maxTemp, Integer minTemp) {
        this.modelName = modelName;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Integer maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Integer getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Integer minTemp) {
        this.minTemp = minTemp;
    }

    @Override
    public String toString() {
        return "CalibrationParameters{" +
                "modelName='" + modelName + '\'' +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                '}';
    }
}
