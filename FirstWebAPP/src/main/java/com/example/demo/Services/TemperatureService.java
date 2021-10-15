package com.example.demo.Services;


import com.example.demo.model.ListModel;
import com.example.demo.model.MainTemperatureDataModel;
import com.example.demo.model.OpenWeatherAPIModel;
import com.example.demo.model.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TemperatureService {

    public Map<Date, Map<String, MainTemperatureDataModel>> getTemperatureDataForPeriod(OpenWeatherAPIModel openWeatherAPIModel, Integer numberOfDays) {

        List<ListModel> listModel = openWeatherAPIModel.getFulldDataList();
        Map<Date, Map<String, MainTemperatureDataModel>> temperatureDataMapWithDates = new HashMap<>();

        AtomicInteger addedDays = new AtomicInteger(0);
        for (ListModel data : listModel) {

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, MainTemperatureDataModel> map = objectMapper.convertValue(data.getMainTemperatureDataModel(), Map.class);
            temperatureDataMapWithDates.put(data.getDt(), map);

            //add weather data

            addedDays.incrementAndGet();

            if (addedDays.get() == numberOfDays) break;
        }

        return temperatureDataMapWithDates;
    }

    public void getTemperatureData(OpenWeatherAPIModel openWeatherAPIModel) {
        getTemperatureDataForPeriod(openWeatherAPIModel, 1);
    }


    public Weather getWeatherDataForPeriod(OpenWeatherAPIModel openWeatherAPIModel) {

        //TODO: Add ability to request for weather for particular date.
        List<Weather> weatherData = openWeatherAPIModel.getFulldDataList().get(0).getWeather();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(weatherData.get(0), Weather.class);

    }

    public Integer getCloudDescriptionFromData(OpenWeatherAPIModel openWeatherAPIModel) {

        //TODO: Add ability to request for weather for particular date.
        return openWeatherAPIModel.getFulldDataList().get(0).getCloud().getAll();
    }
}
