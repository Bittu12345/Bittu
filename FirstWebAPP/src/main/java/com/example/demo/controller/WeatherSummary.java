package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class WeatherSummary {

	private Map city;
	private List list;
	
/*
 * {dt=1574575200,
 *  main={temp=274.06, temp_min=273.92, temp_max=274.06, pressure=1008, sea_level=1008, grnd_level=970, humidity=90, temp_kf=0.14}, 
 *  weather=[{id=600, main=Snow, description=light snow, icon=13n}], clouds={all=98}, wind={speed=5.2, deg=287}, snow={3h=0.38}, sys={pod=n}, dt_txt=2019-11-24 06:00:00}
	
 */
	
	
			
	
	
	public List<Map> getTemperature()
	{
		List<Map> mapList = new ArrayList<>();
		
		Map<String,String> weatherDescription  = new HashMap<String,String>();		
		Map<String, Double> temperatures = new HashMap<String, Double>();
		
		Map<String, String> cloudDescr = new HashMap<String, String>();
		
		
		
		
		LinkedHashMap<String ,Map<String,Double>> listData= (LinkedHashMap<String, Map<String, Double>>) list.get(0);
		
		for (Entry<String, Map<String, Double>> entry : listData.entrySet())
		{
		    String key = entry.getKey();
		    
		    if(entry.getValue() instanceof Map )
		    {
		    	Map<String,Double> value = entry.getValue(); 		  	    
		    
		    	if(key.equalsIgnoreCase("main"))
		    		{
		    	
		    		for (Entry<String, Double> realTemperatures : value.entrySet())
		    			{
		    		//System.out.println("key VALUE "+realTemperatures.getKey());
		    		//System.out.println(realTemperatures.getValue());
		    			if(realTemperatures.getValue() instanceof Double )
		    				temperatures.computeIfAbsent(realTemperatures.getKey(),k-> realTemperatures.getValue());
		    			else
		    				{
		    				Number integerValue = realTemperatures.getValue();
		    				Double doubleValue = integerValue.doubleValue();
		    				temperatures.computeIfAbsent(realTemperatures.getKey(),k-> doubleValue);
		    				}
		    			}	
		    		}
		    
		       if(key.equalsIgnoreCase("clouds"))
		       		{
		    	
		    	   	for (Entry cloudsDescription : value.entrySet())
		    	   		{
		    	   			cloudDescr.computeIfAbsent((String) cloudsDescription.getKey(),k-> cloudsDescription.getValue().toString());
		    	   		}
		       		}
		    	
		    } 
		    else
		    {
		    	if(key.equalsIgnoreCase("weather"))
		    	{
		    		List<Map<String,String>> myList = new ArrayList<Map<String,String>>();
		    		myList  = (List<Map<String, String>>) entry.getValue();
		    		for(Map<String,String> list: myList) {
		    			for ( Entry<String, String> clouds  : list.entrySet()) 
		    			{
		    				if(clouds.getValue() instanceof String)
		    				weatherDescription.put(clouds.getKey(), clouds.getValue());
		    			}
		    		}
		    		
		    	}
		    }
		   
		}
		    
		
		    
		
		mapList.add(weatherDescription);
		mapList.add(temperatures);
		   	
		
		
		
		
		return mapList;
	}
	
	
	
	

	public Map getCity() {
		return city;
	}

	public void setCity(Map city) {
		this.city = city;
	}



	

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "WeatherSummary [city=" + city + ", list=" + list + "]";
	}
	
}