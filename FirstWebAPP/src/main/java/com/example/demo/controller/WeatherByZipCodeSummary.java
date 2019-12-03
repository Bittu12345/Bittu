package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WeatherByZipCodeSummary {

	private List<Map<String,String>> weather;
	private Map<String,Number> main;
	private Map sys;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Map<String, String>> getWeather() {
		return weather;
	}
	public void setWeather(List<Map<String, String>> weather) {
		this.weather = weather;
	}
	public Map getMain() {
		return main;
	}
	public void setMain(Map main) {
		this.main = main;
	}
	public Map getSys() {
		return sys;
	}
	public void setSys(Map sys) {
		this.sys = sys;
	}
	@Override
	public String toString() {
		return "WeatherByZipCodeSummary [weather=" + weather + ", main=" + main + ", sys=" + sys + ", name=" + name
				+ "]";
	}
	
	
	public Map getWeatherInfoByZip()
	{
		Map<String, String> temperatureZipCode =  weather.get(0);
		return temperatureZipCode;
	}
	
	
}
