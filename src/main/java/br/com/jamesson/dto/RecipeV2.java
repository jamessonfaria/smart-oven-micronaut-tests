package br.com.jamesson.dto;

public class RecipeV2 {

    private String name;
    private Integer temp;
    private Integer duration;

    public RecipeV2(){}

    public RecipeV2(String name, Integer temp, Integer duration) {
        this.name = name;
        this.temp = temp;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
