package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CommentDataRepo;
import com.example.demo.model.CommentDataModel;





@Controller
public class WeatherController {
	
	@Autowired
	CommentDataRepo commentDataRepo;
	
	
	@Autowired
	WeatherApiController weatherApiController;
	
	@RequestMapping("/")
	public ModelAndView weatherHome()
	{	
		
		ModelAndView displayTemperatures  = new ModelAndView("welcomePage.jsp");
		displayTemperatures = weatherApiController.getApiCall(displayTemperatures);		
		displayTemperatures = getTemperatureData(displayTemperatures);		
		displayTemperatures = commentWithHighestVotes(displayTemperatures);
		return displayTemperatures;
		 
	}

	
	//gets all the data about temperatures
	public ModelAndView getTemperatureData(ModelAndView mv)
	{
		Map<String , Number> temps = (Map<String, Number>) mv.getModel().get("temperatureData");
		Map<String,String> cloudDesc = (Map<String, String>) mv.getModel().get("cloudDescription");
		System.out.println(cloudDesc);
		
		Number minTemperatureCelsius =  temps.get("temp_min");
		Number maxTemperatureCelsius = temps.get("temp_max");
		Number actualTemperatureCelsius = temps.get("temp");
		Number humidity = temps.get("humidity");
		Number pressure = temps.get("pressure");
		
		String description = cloudDesc.get("description");
		String weatherIcon = cloudDesc.get("icon");
		String main = cloudDesc.get("main");
		
		String minTemperatureFarenheit = convertCelsiusToFarenhiet(minTemperatureCelsius);
		
		
		String maxTemperatureFarenheit = convertCelsiusToFarenhiet(maxTemperatureCelsius);
		
		
		String actualTemperatureFarenheit = convertCelsiusToFarenhiet(actualTemperatureCelsius);
		
		mv.addObject("minTemperatureCelsius", minTemperatureCelsius);
		mv.addObject("maxTemperatureCelsius", maxTemperatureCelsius);

		mv.addObject("actualTemperatureCelsius", actualTemperatureCelsius);
		mv.addObject(minTemperatureFarenheit, minTemperatureFarenheit);
		
		mv.addObject(maxTemperatureFarenheit,maxTemperatureFarenheit);;
		mv.addObject(actualTemperatureFarenheit, actualTemperatureFarenheit);

		mv.addObject("humidity", humidity);
		mv.addObject("pressure", pressure);
		
		mv.addObject("description",description);
		mv.addObject("weatherIcon",weatherIcon);
		mv.addObject("main",main);
		return mv;
	}
	
   private String convertCelsiusToFarenhiet(Number temperatureCelsius) {
		
	   if(temperatureCelsius instanceof Double) {
		   return "" +( ((Double)temperatureCelsius * 9/5) +32) +"";
		
	   }
	   else
		   return null;
	   
	}	

@RequestMapping("/addComments")
	public ModelAndView addComments(CommentDataModel commentModel)
	{
	
		CommentDataModel userNameExists = 	commentDataRepo.findByuserName(commentModel.getUserName());
		System.out.println(userNameExists);
		if(userNameExists == null)
		{
		
			
		commentDataRepo.save(commentModel);	
		
		Map<String,String>  addedComment= new HashMap<String, String>();		
		
		
		
		ModelAndView commentDataModelView = new ModelAndView();
		commentDataModelView =  generateDataBaseComments(commentDataModelView);
		
		addedComment.put("comments",commentModel.getComments());
		addedComment.put("userName", commentModel.getUserName());
		
		commentDataModelView.addObject("bool", true);
		
		
		Map<String,String>  databaseAllComments = (Map<String, String>) commentDataModelView.getModel().get("databaseAllComments");
		
		
		weatherApiController.getApiCall(commentDataModelView);
		commentDataModelView = getTemperatureData(commentDataModelView);
		
		commentDataModelView.addAllObjects(addedComment);
		commentDataModelView = commentWithHighestVotes(commentDataModelView);
		commentDataModelView.setViewName("welcomePage.jsp");
		return commentDataModelView;
		}
		else
		{
			ModelAndView commentDataModelView = new ModelAndView("welcomePage.jsp");
			commentDataModelView.addObject("userName", commentModel.getUserName());
			commentDataModelView.addObject("bool", false);
			commentDataModelView = commentWithHighestVotes(commentDataModelView);
			return commentDataModelView;
		}
	   
	}  
   
 @RequestMapping("/upVote")
 public ModelAndView  upVote(@RequestParam String userName)
 {
	 ModelAndView addUpVote = new ModelAndView("welcomePage.jsp");
	 CommentDataModel getUsernameEntity =  commentDataRepo.findByuserName(userName);
	
	 getUsernameEntity.setUpVotes(getUsernameEntity.getUpVotes() + 1 );
	 commentDataRepo.save(getUsernameEntity);
	 
	 addUpVote = generateDataBaseComments(addUpVote);
	 addUpVote = weatherApiController.getApiCall(addUpVote);		
	 addUpVote =getTemperatureData(addUpVote);
	 addUpVote = commentWithHighestVotes(addUpVote);
	 
	 return addUpVote;
 }
	 
	   

   
	public ModelAndView generateDataBaseComments(ModelAndView modelView)
	{
		
		Map<String,Map<String,Long>>  databaseAllComments= new HashMap<String, Map<String,Long>>();
		
		Iterable<CommentDataModel> commentInfo  = commentDataRepo.findAll();
		for(CommentDataModel info: commentInfo)
		{	
		Map<String,Long> temporaryMap = new HashMap<String,Long>();
		temporaryMap.put(info.getComments(), info.getUpVotes());
		
		databaseAllComments.computeIfAbsent(info.getUserName(), k->temporaryMap);
		
		}
		
		modelView.addObject("databaseAllComments", databaseAllComments);
	
		return modelView;
		
	}
	
	public ModelAndView commentWithHighestVotes(ModelAndView modelView)
	{
		long highestVotes = 0;
		String highestVotedComment  = null;
		String votesUserName = null;
		Iterable<CommentDataModel> commentInfo  = commentDataRepo.findAll();
		for(CommentDataModel info : commentInfo)
		{
		
			if(highestVotes < info.getUpVotes())

			{
				highestVotes = info.getUpVotes();
				highestVotedComment = info.getComments();
				votesUserName = info.getUserName();
				
			}
		}
		if(highestVotes == 0)
		{
			modelView.addObject("highestVotes", false);
		}
		else
		{		
		modelView.addObject("highestVotedComment", highestVotedComment);
		modelView.addObject(votesUserName,votesUserName);
		modelView.addObject("highestVotes",highestVotes);
		}
		return modelView;
	}
	
}
	
	
