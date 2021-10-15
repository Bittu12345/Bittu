<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
<meta charset="ISO-8859-1">
<title>WeatherApp</title>
<link rel="stylesheet" type = "text/css" href="/css/AllStyles.css"/>
<script type = "text/javascript" src="/js/javaScriptFile.js"> </script>
</head>
<body class = "body">
<div class="content">
  <h1>Dayton Weather</h1>
 
</div>
<div id = "mainContent" class = "center">

<div id= "zipcode" class ="zipCodeForm" >
<form  action = "zipsearch">
<input type = "text" name = "zipcode" placeholder = "Search by Zipcode">
<input type = "submit" value= "search">
</form>
</div>







 <div id="icon">
 <img id="wicon" value = "${weatherIcon}" src="" alt="Weather icon">
 </div>
 
 <script type="text/javascript">
 displayIcon();
 </script>


 <c:forEach items="${temperatureDataWithDates}" var="dates">
    <tr>

           Temperatures as of :  <td>${dates.key}</td>
           <br>
            <c:forEach items="${dates.value}" var="temperature" >

            <td>${temperature.key}  - ${temperature.value}</td>

           <br>

            </c:forEach>

        <br>
      </tr>
    </c:forEach>

<div>
Forecast feelsLike : <label id  = " feelsLike">  ${highestVotedComment} </label><br>







<div id = "displayResults"  value  = "${bool}"> 
<label id = "successLabel">${userName}  and  comment : ${comments} added Successfully! </label> 
<label id = "failedLabel">${userName}  is already taken! try with another one </label> 
</div>

<script type="text/javascript">

  hideOrDisplay();
</script>
<form action="addComments">

<input type = "hidden" name = "city" value = "${cityName}" >
<input type = "text" name = "userName" placeholder = "yourUserName">
<br>
<input type = "hidden" name =  "like" value = 0>
<textarea rows="4" cols="60" rows ="10" name="comments" placeholder="how does it feel like?"></textarea>
<input type  = "submit" value = "Add Information">

</form>
<div id  = "tableDisplay" >
<table id = "tabelId">
 <tr>
    <th>Username</th>
    <th>Comment</th>
    <th>upVotes</th>
  </tr>	

   <c:forEach items="${databaseAllComments}" var="userName">
   <tr>
     
           <td>${userName.key}</td>
           <c:forEach items="${userName.value}" var="comment" >
           	
           <td>${comment.key} </td>
          <td> ${comment.value}  upVotes</td>
               
           </c:forEach>       
           
           
          	 <td>  
           		<form action="upVote">

				<input type = "hidden" name = "userName" value = "${userName.key}">
				<br>
				
				<input class = "upvote" title = "Upvote" type="image" src="https://icon-library.net//images/upvote-icon/upvote-icon-17.jpg" width = 40px; alt="upVote">

				</form>
			</td>
     </tr>
   </c:forEach>
</table>
</div>
</div>
</div>
</body>
</html>