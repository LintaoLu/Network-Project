function addFile()
{
    var file = document.getElementById("file").files[0];
    var fileContent = getBase64(file);
    // fileContent = preprocessFile(fileContent);
    // console.log(fileContent);
    // sendRequest(fileName);
}

function sendFile(file)
{
    var data = new FormData();
    data.append("file", file);

    const Http = new XMLHttpRequest();
    const url = 'http://localhost:8080/file';

    Http.open("POST", url);
    Http.setRequestHeader("Access-Control-Allow-Origin", "*");
    Http.send(data);
    Http.onreadystatechange = function () {
        console.log(Http.responseText);
    }
        
}

function getBase64(file) 
{
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
        var result = reader.result;
        result = preprocessFile(result);
        sendFile(result);
    }
 }

 function preprocessFile(fileContent)
 {
     var fileString = fileContent.split(",")[1];
     return fileString;
 }

 function findAllPaths()
 {
	document.getElementById("solution").innerHTML = "";
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:8080/allPaths';

    Http.open("GET", url);
    Http.setRequestHeader("Access-Control-Allow-Origin", "*");
    Http.send();
    Http.onload = function () {
        console.log((Http.responseText));
		//for(var i=0; i< Http.response.length; i++){
		//	var solution = JSON.parse(Http.response)[i];
		//	 document.getElementById("solution").append(solution);
		//}
		document.getElementById("solution").append(Http.response);
    }
 }


 function findShortestPath()
 {
	document.getElementById("solution").innerHTML = "";
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:8080/shortestPath';

    Http.open("GET", url);
    Http.setRequestHeader("Access-Control-Allow-Origin", "*");
    Http.send();
    Http.onload = function () {
        document.getElementById("solution").append(Http.response);
    }	
 }
 
 
 function findShoetestPathCost(){
	 document.getElementById("solution").innerHTML = "";
	 const Http = new XMLHttpRequest();
	  const url = 'http://localhost:8080/shortestpathcost';
	  
	  Http.open("GET",url);
	  Http.setRequestHeader("Access-Control-Allow-Origin", "*");
	  Http.send();
	  Http.onload = function () {
		  document.getElementById("solution").append(Http.response);
	  }
 }
	 