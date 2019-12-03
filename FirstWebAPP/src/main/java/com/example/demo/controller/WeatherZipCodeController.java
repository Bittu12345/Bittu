package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CityCommentDataRepo;
import com.example.demo.dao.CommentDataRepo;
import com.example.demo.model.CityCommentsDataModel;
import com.example.demo.model.CommentDataModel;
@RestController
public class WeatherZipCodeController {
	
	@Autowired
	CityCommentDataRepo cityCommentDataRepo;
	
	
	@Autowired
	WeatherApiController weatherApiController;

	String mainZipCode ;
	@RequestMapping("/zipsearch")	
	public ModelAndView searchByZip(@RequestParam String zipcode)
	{
		ModelAndView searchByZip  = new ModelAndView();
			searchByZip = 	weatherApiController.getApiCallByZipCode(zipcode,searchByZip);
		

		searchByZip = getWeatherZip(searchByZip);
		mainZipCode = zipcode;
		searchByZip = getMainZip(searchByZip);
		searchByZip = getCityDatabaseComments(searchByZip);
	
		searchByZip.setViewName("ZipCode.jsp");
		return searchByZip;
		
	}
	
	@RequestMapping("/zipAddComments")
	public ModelAndView addCityComments(CityCommentsDataModel cityCommentsModel)
	{
		cityCommentDataRepo.save(cityCommentsModel);
		ModelAndView cityComments = new ModelAndView();
		
		if(mainZipCode != null)
		{
		cityComments = weatherApiController.getApiCallByZipCode(mainZipCode, cityComments);		
		cityComments = getWeatherZip(cityComments);		
		cityComments = getMainZip(cityComments);
		cityComments = getCityDatabaseComments(cityComments) ;
		
		
		
		cityComments.setViewName("ZipCode.jsp");
		return cityComments;
		}
		else
		{
			cityComments.addObject("MESSAGE", "error");
			cityComments.setViewName("ZipCode.jsp");
			return cityComments;
		}
	}
	
	
	public ModelAndView getCityDatabaseComments(ModelAndView generateCityComments) 
	
		{
		
		Map<String,List<String>>  databaseAllComments= new HashMap<String, List<String>>();
		String city = (String) generateCityComments.getModel().get("cityName");
		if(city != null)
		{
		List<CityCommentsDataModel> getCityComments = cityCommentDataRepo.findBycity(city);
		
		
		
		for(CityCommentsDataModel info : getCityComments)
		{
			
			databaseAllComments.computeIfAbsent(info.getUserName() , k-> new ArrayList<String>()).add(info.getComments());
		}
		
		generateCityComments.addObject("databaseAllComments", databaseAllComments);
		System.out.println(databaseAllComments);
		return generateCityComments;
		}
		else {
			return generateCityComments;
		}
		}
	

	public ModelAndView getMainZip(ModelAndView mv) {
		Map<String , Number> temperaturesByZip = (Map<String, Number>) mv.getModel().get("main");
		Number minTemperatureCelsius =   temperaturesByZip.get("temp_min");
		
		Number maxTemperatureCelsius =  temperaturesByZip.get("temp_max");
		Number actualTemperatureCelsius = temperaturesByZip.get("temp");
		Number humidity = temperaturesByZip.get("humidity");
		Number pressure = temperaturesByZip.get("pressure");
		mv.addObject("minTemperatureCelsius", minTemperatureCelsius);
		mv.addObject("maxTemperatureCelsius", maxTemperatureCelsius);

		mv.addObject("actualTemperatureCelsius", actualTemperatureCelsius);
		mv.addObject("humidity", humidity);
		mv.addObject("pressure", pressure);
		return mv;
	}

	public ModelAndView getWeatherZip(ModelAndView mv) {
		Map<String , String> weather = (Map<String, String>) mv.getModel().get("weatherInfo");
		String main = weather.get("main");
		String description = weather.get("description");
		String icon = weather.get("icon");
		mv.addObject("weatherMain",main);
		mv.addObject("description",description);
		mv.addObject("icon", icon);
		return mv;
	}

}
