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
	var from = document.getElementById("allPathsFrom").value;
	var to = document.getElementById("allPathsTo").value;
	var data = new FormData();
	data.append("from", from);
	data.append("to", to);
	
	document.getElementById("solution").innerHTML = "";
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:8080/allPaths';

    Http.open("POST", url);
    Http.setRequestHeader("Access-Control-Allow-Origin", "*");
    Http.send(data);
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
	var from = document.getElementById("shortestPathFrom").value;
	var to = document.getElementById("shortestPathTo").value;

	var data = new FormData();
	data.append("from", from);
	data.append("to", to);

	document.getElementById("solution").innerHTML = "";
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:8080/shortestPath';

    Http.open("POST", url);
    Http.setRequestHeader("Access-Control-Allow-Origin", "*");
    Http.send(data);
    Http.onload = function () {
        document.getElementById("solution").append(Http.response);
    }	
 }
 
 
 function findShoetestPathCost(){
	 
	 var from = document.getElementById("shortestPathCostFrom").value;
	 var to = document.getElementById("shortestPathCostTo").value;

	var data = new FormData();
	data.append("from", from);
	data.append("to", to);
	
	 document.getElementById("solution").innerHTML = "";
	 const Http = new XMLHttpRequest();
	  const url = 'http://localhost:8080/shortestpathcost';
	  
	  Http.open("POST",url);
	  Http.setRequestHeader("Access-Control-Allow-Origin", "*");
	  Http.send(data);
	  Http.onload = function () {
		  document.getElementById("solution").append(Http.response);
	  }
 }
 
 function deleteNode(){
	var nodeNumber = document.getElementById("deleteNodeNumber").value;
	
	var data = new FormData();
	data.append("nodeNumber", nodeNumber);
	
	document.getElementById("solution").innerHTML = "";
	const Http = new XMLHttpRequest();
	const url = 'http://localhost:8080/deleteNode';
	
	Http.open("POST",url);
	  Http.setRequestHeader("Access-Control-Allow-Origin", "*");
	  Http.send(data);
	  Http.onload = function () {
		  document.getElementById("solution").append(Http.response);
	  }
 }
 
 function reset(){
	document.getElementById("solution").innerHTML = "";

	const Http = new XMLHttpRequest();
	const url = 'http://localhost:8080/reset';
	
	Http.open("GET",url);
	  Http.setRequestHeader("Access-Control-Allow-Origin", "*");
	  Http.send();
	  Http.onload = function () {
		  document.getElementById("solution").append("reset successful");
	  }
 } 
 
 function addNode(){
	document.getElementById("solution").innerHTML = "";

	const Http = new XMLHttpRequest();
	const url = 'http://localhost:8080/addnode';

	var to = document.getElementById("to").value;
	var from = document.getElementById("from").value;
	var costTo = document.getElementById("costTo").value;
	var costFrom = document.getElementById("costFrom").value;
	
	var data = new FormData();

	data.append("to", to)
	data.append("from", from)
	data.append("costTo", costTo)
	data.append("costFrom", costFrom)
	
	Http.open("POST",url);
	  Http.setRequestHeader("Access-Control-Allow-Origin", "*");
	  Http.send(data);
	  Http.onload = function () {
		  document.getElementById("solution").append(Http.response);
	  }
 }