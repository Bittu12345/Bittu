package com.example.demo.model;

import com.example.demo.Helper.UnixTimeStampDeserializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListModel {


    @JsonDeserialize(using = UnixTimeStampDeserializer.class)
    private Date dt;
    @JsonProperty("main")
    private MainTemperatureDataModel mainTemperatureDataModel;
    private List<Weather> weather;
    private String visibility;
    private Integer pop;
    @JsonProperty("cloud")
    private CloudModel cloud;


    public CloudModel getCloud() {
        return cloud;
    }

    public void setCloud(CloudModel cloud) {
        this.cloud = cloud;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public MainTemperatureDataModel getMainTemperatureDataModel() {
        return mainTemperatureDataModel;
    }

    public void setMainTemperatureDataModel(MainTemperatureDataModel mainData) {
        this.mainTemperatureDataModel = mainData;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List weather) {
        this.weather = weather;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Integer getPop() {
        return pop;
    }

    public void setPop(Integer pop) {
        this.pop = pop;
    }

}
