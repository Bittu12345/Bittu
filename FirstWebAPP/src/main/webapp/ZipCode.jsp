<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type = "text/css" href="/css/AllStyles.css"/>
<script type = "text/javascript" src="/js/javaScriptFile.js"> </script>
<meta charset="ISO-8859-1">
<title> ${cityName} </title>
</head>
<body class = "body">
<div class="content">
  <h1>${cityName} Weather</h1> 
</div>


<div id = "mainContent" class = "center">

<div id= "zipcode" class ="zipCodeForm" >
<form  action = "zipsearch">
<input type = "text" name = "zipcode" placeholder = "Search by Zipcode">
<input type = "submit" value= "search">
</form>
</div>




<div id = "dispalyTemperatureData">
<label id = "actualTemp">Actual Temperature: ${actualTemperatureCelsius} &#8451</label><br>
<label id = "maxTemp">Maximum Temperature: ${maxTemperatureCelsius} &#8451</label><br>
<label id  = "minTemp">Minimum Temperature: ${minTemperatureCelsius} &#8451</label><br>
 <label id= "humidity" >Humidity - ${humidity} %</label><br>
<label id ="pressure" >Pressure - ${pressure} hPa</label>
 
</div>

<div id="icon">

 <img id="wicon" value = "${icon}" src="" alt="Weather icon">
 <label id= "description" > ${weatherMain} - ${description} </label>
 </div>
<script type="text/javascript">
 displayIcon();
 </script>
 
 <div id = "formDisplay" value = "${cityName}">
 	<form action="zipAddComments">

<input type = "hidden" name = "city" value = "${cityName}" >
<input type = "text" name = "userName" placeholder = "yourUserName">
<br>
<input type = "hidden" name =  "like" value = 0>
<textarea rows="4" cols="60" rows ="10" name="comments" placeholder="Commenting on ${cityName}"></textarea>
<input type  = "submit" value = "Comment">

</form>
 
 <div id  = "tableDisplay" value = "${databaseAllComments}">
<table id = "tabelId">
 <tr>
    <th>Username</th>
    <th>Comment</th>
   
  </tr>	

   <c:forEach items="${databaseAllComments}" var="userName">
   <tr>
     
           <td>${userName.key}</td>
           <c:forEach items="${userName.value}" var="comment" >
           	
           <td>${comment} </td>
          
               
           </c:forEach>       
           
           
     </tr>
   </c:forEach>
</table>
</div>

 
 </div>
 
 
</div>
</body>
</html>