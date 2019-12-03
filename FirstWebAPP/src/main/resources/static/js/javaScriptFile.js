/**
 * 
 */

function hideOrDisplay()
{
	var displayResults = document.getElementById("displayResults")
	var successLabel = document.getElementById("successLabel");
	var failedLabel =  document.getElementById("failedLabel")
	console.log(displayResults.attributes.value);
	if(displayResults.attributes.value.value === "true")
		{
		
		displayResults.className = "successFull";
		failedLabel.style.display = "none";
		}
	else if(displayResults.attributes.value.value === "false")
	{
	displayResults.className = "error";
	successLabel.style.display = "none";
	}
	else
		{
		displayResults.style.display = "none";
		}
	
}
function displayIcon()
{
	var weatherIcon = document.getElementById("wicon");
	var iconcode = weatherIcon.attributes.value.value;
	
var iconurl = "http://openweathermap.org/img/w/" + iconcode + ".png";

weatherIcon.src = iconurl;

}


