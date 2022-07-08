package br.com.jamesson.dto;

public class Recipe {

    private String name;
    private Integer cookingTemperature;
    private Integer cookingDuration;

    public Recipe(){}

    public Recipe(String name, Integer cookingTemperature, Integer cookingDuration) {
        this.name = name;
        this.cookingTemperature = cookingTemperature;
        this.cookingDuration = cookingDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCookingTemperature() {
        return cookingTemperature;
    }

    public void setCookingTemperature(Integer cookingTemperature) {
        this.cookingTemperature = cookingTemperature;
    }

    public Integer getCookingDuration() {
        return cookingDuration;
    }

    public void setCookingDuration(Integer cookingDuration) {
        this.cookingDuration = cookingDuration;
    }
}
