package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherAPIModel {

    private String cod;
    private String message;

    @JsonProperty("list")
    private List<ListModel> listDataModel;

    @JsonProperty("city")
    private CityModel cityData;


    public OpenWeatherAPIModel(String cod, String message, List<ListModel> listDataModel, CityModel cityData) {
        this.cod = cod;
        this.message = message;
        this.listDataModel = listDataModel;
        this.cityData = cityData;
    }

    public OpenWeatherAPIModel() {

    }

    @Override
    public String toString() {
        return "MainModel{" +
                "cod='" + cod + '\'' +
                ", message='" + message + '\'' +
                ", listDataModel=" + listDataModel +
                ", cityData=" + cityData +
                '}';
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListModel> getFulldDataList() {
        return listDataModel;
    }

    public void setFulldDataList(List<ListModel> listDataModel) {
        this.listDataModel = listDataModel;
    }

    public CityModel getCityData() {
        return cityData;
    }

    public void setCityData(CityModel cityData) {
        this.cityData = cityData;
    }
}
