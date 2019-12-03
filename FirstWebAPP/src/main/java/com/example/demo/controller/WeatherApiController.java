package com.example.demo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController

public class WeatherApiController {
	@Value("${api.key}")
	private String apiKey;
	private String apiUrl;
	
	public String getApiUrl() {
		//https://samples.openweathermap.org/data/2.5/find?q=London&units=metric&appid=b6907d289e10d714a6e88b30761fae22
		return "http://api.openweathermap.org/data/2.5/forecast?q=Dayton,us&units=metric&APPID="+apiKey;
	}
	
	public String getApiUrlZipcode(String zipCode)
	{
		
		return "http://api.openweathermap.org/data/2.5/weather?zip="+zipCode+",us&units=metric&APPID="+apiKey;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	@Autowired
	private RestTemplate restTemplate;
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
		
		WeatherSummary temperatures = restTemplate.getForObject(getApiUrl(),WeatherSummary.class);		
		List<Map> temperatureData =   temperatures.getTemperature();
		mv.addObject("cloudDescription", temperatureData.get(0));
		mv.addObject("temperatureData", temperatureData.get(1));
		return mv;
	}
	public ModelAndView getApiCallByZipCode(String zipCode,ModelAndView modelViewObject)
	{
		ModelAndView mv = new ModelAndView();
		WeatherByZipCodeSummary searchByZip =  restTemplate.getForObject(getApiUrlZipcode(zipCode),WeatherByZipCodeSummary.class);
		mv.addObject("weatherInfo",searchByZip.getWeatherInfoByZip());
		mv.addObject("main" , searchByZip.getMain());
		
		mv.addObject("cityName" , searchByZip.getName());
		return mv;
	}
	
	
	
}
