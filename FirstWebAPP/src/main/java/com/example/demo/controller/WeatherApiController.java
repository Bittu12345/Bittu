package com.example.demo.controller;

import com.example.demo.Services.TemperatureService;
import com.example.demo.model.MainTemperatureDataModel;
import com.example.demo.model.OpenWeatherAPIModel;
import com.example.demo.model.Weather;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;
import java.util.Date;
import java.util.Map;

@RestController

public class WeatherApiController {

    Logger logger = LoggerFactory.getLogger(WeatherApiController.class);
    @Value("${api.key}")
    private String apiKey;
    private String apiUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TemperatureService _tempService;

    public WeatherApiController() {
    }

    public String getApiUrl() {
        //https://samples.openweathermap.org/data/2.5/find?q=London&units=metric&appid=b6907d289e10d714a6e88b30761fae22
        return "http://api.openweathermap.org/data/2.5/forecast?q=Dayton,us&units=metric&APPID=" + apiKey;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrlZipcode(String zipCode) {

        return "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&units=metric&APPID=" + apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "WeatherApiController [apiKey=" + apiKey + "]";
    }

    public ModelAndView getApiCall(ModelAndView mv) {

        OpenWeatherAPIModel openWeatherAPIModel = null;
        try {
            URL url = new URL(getApiUrl());
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            openWeatherAPIModel = objectMapper.readValue(url, OpenWeatherAPIModel.class);
        } catch (Exception e) {

            logger.error("Error while getting Data from OpenWeatherApi{}", e);

        }

        Map<Date, Map<String, MainTemperatureDataModel>> temperatureData = _tempService.getTemperatureDataForPeriod(openWeatherAPIModel, 1);
        Weather weatherData = _tempService.getWeatherDataForPeriod(openWeatherAPIModel);


        mv.addObject("cloudDescription", weatherData);
        mv.addObject("temperatureData", temperatureData);
        return mv;
    }

    public ModelAndView getApiCallByZipCode(String zipCode, ModelAndView modelViewObject) {
        ModelAndView mv = new ModelAndView();
        WeatherByZipCodeSummary searchByZip = restTemplate.getForObject(getApiUrlZipcode(zipCode), WeatherByZipCodeSummary.class);
        mv.addObject("weatherInfo", searchByZip.getWeatherInfoByZip());
        mv.addObject("main", searchByZip.getMain());

        mv.addObject("cityName", searchByZip.getName());
        return mv;
    }


}
