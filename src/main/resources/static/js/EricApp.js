/**
 * 
 */

function myMove(player, index1, index2){
	var interval = setInterval(moveObject, 250);
	function moveObject(){
	    index1++;
	    index1 = (index1%40);
	    $(`#${player}`).appendTo(`#space${index1}`);
	    if (index1 == index2){
	        clearInterval(interval);
	    }
	}
}



var gamestate = "";
var webSocket = new WebSocket('ws://localhost:8080/chatServerEndPoint');
var myTurn = false;
var receivedTrade = "";
window.onload = function() {
	var str = document.getElementById("usersTextArea").value;
	console.log(str);
	webSocket.send(JSON.stringify({
		'message' : str
	}));
	webSocket.send(JSON.stringify({
		'action' : 'gamestate'
	}));
	};

	

webSocket.onmessage = function processMessage(incomingMessage) {
	var jsonData = JSON.parse(incomingMessage.data);
	console.log(jsonData);

	
	if (jsonData.messageType == "ChatMessage"){		
		messagesTextArea.value += jsonData.name+ ":  " + jsonData.message + '\n';		
	}
	else if(jsonData.messageType == "TurnMessage") {
		myTurn = jsonData.myTurn;
		console.log("Received turn message. Value: " + jsonData.myTurn);
	}
	
	else if(jsonData.messageType == "GamestateMessage") {
		gamestate = JSON.parse(jsonData.gamestate);	
		var playersList = gamestate.players;
		console.log("player list = "+ playersList);
		
		//Building player information boxes
		for (var i=0; i<playersList.length;i++){
			console.log("player = "+playersList[i].name + playersList[i].money);
			$('#player'+(i+1)).show();
			$('#playerbox'+(i+1)).show();
			if(gamestate.currentPlayerIndex === i) {
				$('#playerbox'+(i+1)).addClass('active-player');
			}
			else {
				$('#playerbox'+(i+1)).removeClass('active-player');
				
			}
			document.getElementById("player"+(i+1)+"info").innerHTML = "<div class='col-4'>"+playersList[i].name+"</div><div class='col-4'>" + "$" + playersList[i].money +"</div>"; 
			document.getElementById("listproperties"+(i+1)).innerHTML="";
			for ( var j=0;j< playersList[i].ownedProperties.length;j++){
		
				var ul = document.getElementById("listproperties"+(i+1));
				var li = document.createElement("li");
				li.className = "list-group-item";
				li.style.backgroundColor = playersList[i].ownedProperties[j].color;
				  li.appendChild(document.createTextNode(playersList[i].ownedProperties[j].name));
				  ul.appendChild(li);
			}	
		}
		
		// Start of trade window logic
		var tradeWith = document.getElementById("tradeWith");
		$("#tradeWith").empty();
		var newOption = document.createElement("option");
		newOption.text="--Select--";
		newOption.value="";
		tradeWith.add(newOption);
		for(var i = 0; i <playersList.length; i++){
			if(i != gamestate.currentPlayerIndex){
				var tradeOption = document.createElement("option");
				tradeOption.text = playersList[i].name;
				tradeOption.value = playersList[i].playerID;
				tradeOption.id = playersList[i].name;
				tradeWith.add(tradeOption);
			}

		}
		var giveProperties = document.getElementById("offerProperties");
		$("#offerProperties").empty();
		for(var i = 0; i < playersList[gamestate.currentPlayerIndex].ownedProperties.length; i++){
			var givePropertiesOption = document.createElement("option");
			givePropertiesOption.text = playersList[gamestate.currentPlayerIndex].ownedProperties[i].name;
			givePropertiesOption.value = playersList[gamestate.currentPlayerIndex].ownedProperties[i].name;
			givePropertiesOption.id = playersList[gamestate.currentPlayerIndex].name;
			givePropertiesOption.style.backgroundColor = playersList[gamestate.currentPlayerIndex].ownedProperties[i].color;
			giveProperties.add(givePropertiesOption);
		}
		
		var wantProperties = document.getElementById("requestProperties");
		$("#requestProperties").empty();
		$(document).ready(function(){
			$("#tradeWith").change(function(){
				var tradeWith = $(this).val();
				$("#requestProperties").empty();
				for(var i = 0; i <playersList.length; i++){
					if(tradeWith.valueOf() === playersList[i].playerID.valueOf()){
						for(var j = 0; j < playersList[i].ownedProperties.length; j++){
							var wantPropertiesOption = document.createElement("option");
							console.log(playersList[i].ownedProperties[j].name);
							wantPropertiesOption.text = playersList[i].ownedProperties[j].name;
							wantPropertiesOption.value = playersList[i].ownedProperties[j].name;
							wantPropertiesOption.id = playersList[i].name;
							wantPropertiesOption.style.backgroundColor = playersList[i].ownedProperties[j].color;
							wantProperties.add(wantPropertiesOption);
						}
					}
				}
			});
		});
		document.getElementById("playerMoney").innerHTML = "You have $"+playersList[gamestate.currentPlayerIndex].money;
		// End of trade window logic
		
		
		//House building logic, functioning but needs to change to server variables
		var buildSet = document.getElementById("buildSet");
		$("#buildSet").empty();
		$('#buildingBody').hide();
		$("#building tr").remove();
		var newOption = document.createElement("option");
		newOption.text="--Select--";
		newOption.value="";
		buildSet.add(newOption);
		var properties = playersList[gamestate.currentPlayerIndex].setsOwned;
		var fullSets = gamestate.sets;
		for(var [key, value] of properties.keys()){
			if(key != "railroad" && key != "utility"){
				for(var [key1, value1] of fullSets.keys()){
					if(key.valueOf() == key1.valueOf()){
						if(value == value1){
							var newOption = document.createElement("option");
							newOption.text=key1;
							newOption.value="tomato";
							buildSet.add(newOption);
						}
					}
				}
			}
		}
		
		//"Test" House building logic, need to change for loop to setsOwned , but used server variables
		$(document).ready(function(){
			$("#buildSet").change(function(){
				var buildSet = $(this).val();
				let setOwnedProperties = [];
				var properties = playersList[gamestate.currentPlayerIndex].ownedProperties;
				for(let i = 0; i < properties.length; i++) {
					if(properties[i].color == buildSet) {
						setOwnedProperties.push(properties[i]);
					}
				}
				let minHouses = setOwnedProperties[0].numHouses;
				let maxHouses = setOwnedProperties[0].numHouses;
				for(let i = 0; i < setOwnedProperties; i++) {
					minHouses = Math.min(minHouses, setOwnedProperties[i].numHouses);
					maxHouses = Math.max(maxHouses, setOwnedProperties[i].numHouses);
				}
				$('#buildingBody').show();
				$("#building tr").remove();
				if(buildSet.valueOf() == "" || buildSet.valueOf() == "railroad" || buildSet.valueOf() == "utility"){
					$('#buildingBody').hide();
				}
				else {
					for(let i = 0; i < setOwnedProperties.length; i++) {
						var table= document.getElementById("building");
						var row = table.insertRow(0);
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						var cell3 = row.insertCell(2);
						cell1.innerHTML = setOwnedProperties[i].name;
						cell1.style.backgroundColor = setOwnedProperties[i].color;
						cell2.innerHTML = setOwnedProperties[i].numHouses;
						if(setOwnedProperties[i].numHouses <= minHouses && setOwnedProperties[i].numHouses < 5) {
							var buildBtn = document.createElement('input');
							buildBtn.type = "button";
							buildBtn.className = "btn btn-primary btn-sm mx-1";
							buildBtn.value = "Build";
							cell3.appendChild(buildBtn);
						}
						if(setOwnedProperties[i].numHouses > 0 && setOwnedProperties[i].numHouses == maxHouses) {
							let sellBtn = document.createElement('input');
							sellBtn.type = "button";
							sellBtn.className = "btn btn-danger btn-sm mx-1";
							sellBtn.value = "Sell";
							cell3.appendChild(sellBtn);
						}
					}
				}
			})
		})
		//End of house building "test" logic
		
		
		//var houseBuild = gamestate.players[gamestate.currentPlayerIndex].setsOwned;

		
		
		//Building activity log
		document.getElementById('activity-log').innerHTML = '';
		for (var a=0; a<gamestate.activityLog.length;a++){
			var p = document.createElement("P");
			p.innerHTML = gamestate.activityLog[a];
			document.getElementById('activity-log').appendChild(p);
			
		}
		
		
		if(myTurn == false){
			$('#end-button').hide();
			$('#roll-button').hide();
			$('#buy-button').hide();
		}
		
		else {
			let currentIndex = gamestate.currentPlayerIndex;
			if (playersList[currentIndex].doubleRolls == 0  && playersList[currentIndex].hasRolled == false ){
				$('#end-button').hide();
				$('#roll-button').show();
			}
			
			else if (gamestate.board[playersList[currentIndex].currentLocation].ownedBy === null  ){
				$('#buy-button').show();
			}
			else if (gamestate.board[playersList[currentIndex].currentLocation].ownedBy !== null  || gamestate.board[playersList[currentIndex].currentLocation].ownedBy === undefined ){
				$('#buy-button').hide();
			}
			if (playersList[currentIndex].hasRolled == true && playersList[currentIndex].doubleRolls == 0){
				$('#end-button').show();
				$('#roll-button').hide();
			}

		}

		console.log(gamestate);
	}
	
	else if (jsonData.messageType == "DiceMessage"){
		var dice = jsonData.dice1 + jsonData.dice2;
		rolldice(jsonData.dice1, jsonData.dice2);
	    var count = 0;
	    var id = setInterval(frame2, 1000);
	    function frame2() {
	    	if(count > 0) {
	    		clearInterval(id);
	    		let startLocation = 0;
	    		if(jsonData.finalLocation - (jsonData.dice1 + jsonData.dice2) < 0) {
	    			startLocation = 40 + (jsonData.finalLocation - (jsonData.dice1 + jsonData.dice2));
	    		} 
	    		else {
	    			startLocation = jsonData.finalLocation - (jsonData.dice1 + jsonData.dice2);
	    		}
	    		currentPlayer = "";
	    		switch(gamestate.currentPlayerIndex) {
	    		case 1:
	    			currentPlayer="player2";
	    			break;
	    		case 2:
	    			currentPlayer="player3";
	    			break;
	    		case 3:
	    			currentPlayer="player4";
	    			break
	    		default:
	    			currentPlayer="player1";
	    		}
	    		console.log("moving " + currentPlayer + " from " + startLocation + " to " + jsonData.finalLocation );
	    		myMove(currentPlayer, startLocation, jsonData.finalLocation);
	    		messagesTextArea.value += jsonData.name+ ":  " + dice + '\n';
	    	}
	    	else {
	    		count++;
	    	}
	    }
		
		
	} else if (jsonData.messageType == "TradeMessage") {
		document.getElementById('trade-zone').innerHTML = '';
		$('#trade-zone').show();
		receivedTrade = JSON.parse(jsonData.trade);	
		for (var i=0; i< gamestate.players.length;i++){
			if (gamestate.players[i].playerID == receivedTrade.sender){
				var sendername  = gamestate.players[i].name;
			}
		}
		var newp= document.createElement("P")
		newp.innerHTML += sendername + " wants to trade : " + "<br />" ;
		for (var i=0; i<receivedTrade.senderProperties.length;i++){
			newp.innerHTML +=receivedTrade.senderProperties[i].name;
		}
		newp.innerHTML += "<br />" +" $ " + receivedTrade.recipientMoney + "<br/>";
		newp.innerHTML += " For your : " + "<br/>";
		for (var i=0; i<receivedTrade.recipientProperties.length;i++){
			newp.innerHTML +=receivedTrade.recipientProperties[i].name;
		}
		newp.innerHTML += "<br />" + " $ " + receivedTrade.senderMoney + "<br/>";
		document.getElementById('trade-zone').appendChild(newp);
		var span1 = document.createElement('span1');
		var span2 = document.createElement('span2');
		span1.innerHTML = "<button id='accept ' class='btn btn-primary btn-sm'  onclick='acceptedOffer()' >Accepted</button>";
		span2.innerHTML = "<button id='reject ' class='btn btn-danger btn-sm'  onclick='rejectedOffer()' >Reject</button>";
		document.getElementById('trade-zone').appendChild(span1);
		document.getElementById('trade-zone').appendChild(span2);
		
	}
	
	else if (jsonData.messageType == "UserMessage") {
		usersTextArea.value = "";
		var i = 0;
		while (i < jsonData.user.length){			
			usersTextArea.value += "<br />" + jsonData.user[i] ;
			i++;
		}
	}

}

function sendTradeOffer() {
	
	var senderArray = $('#offerProperties').val();
	var recipArray = $('#requestProperties').val();
	console.log(senderArray);
	console.log(recipArray);
	let senderProps = "";
	for(let i = 0; i < senderArray.length; i++) {
		if(i != senderArray.length-1) {
			senderProps += senderArray[i] + ",";
		}
		else {
			senderProps += senderArray[i];
		}
	}
	let recipProps = "";
	for(let i = 0; i < recipArray.length; i++) {
		if(i != recipArray.length-1) {
			recipProps += recipArray[i] + ",";
		}
		else {
			recipProps += recipArray[i];
		}
	}
	senderMoney = parseInt(document.getElementById("moneyOffer").value);
	recipientMoney = parseInt(document.getElementById("moneyRequest").value);
	webSocket.send(JSON.stringify({
		'trade' : 'trade',
		'accepted' :'false',
		'rejected' :'false',
		'sender' : gamestate.players[gamestate.currentPlayerIndex].playerID,
		'recipient' : document.getElementById("tradeWith").value,
		'senderProperties' : senderProps,
		'recipientProperties' : recipProps,
		'senderMoney' : senderMoney,
		'recipientMoney' : recipientMoney
	}))
	$('#trade').modal('hide');
}


function acceptedOffer(){
	$('#trade-zone').hide();
	var acceptedOffer = receivedTrade;
	acceptedOffer.accepted = 'true';
	var senderArray = acceptedOffer.senderProperties;
	var recipArray = acceptedOffer.recipientProperties;
	console.log(senderArray);
	console.log(recipArray);
	let senderProps = "";
	for(let i = 0; i < senderArray.length; i++) {
		if(i != senderArray.length-1) {
			senderProps += senderArray[i].name + ",";
		}
		else {
			senderProps += senderArray[i].name;
		}
	}
	let recipProps = "";
	for(let i = 0; i < recipArray.length; i++) {
		if(i != recipArray.length-1) {
			recipProps += recipArray[i].name + ",";
		}
		else {
			recipProps += recipArray[i].name;
		}
	}
	webSocket.send(JSON.stringify({
		'trade': 'trade',
		'accepted' : 'true',
		'rejected' : 'false',
		'sender' : acceptedOffer.sender,
		'recipient' : acceptedOffer.recipient,
		'senderProperties' : senderProps,
		'recipientProperties' : recipProps,
		'senderMoney' : receivedTrade.senderMoney,
		'recipientMoney' : receivedTrade.recipientMoney
	}))
}

function rejectedOffer(){
	$('#trade-zone').hide();
	var acceptedOffer = receivedTrade;
	acceptedOffer.accepted = 'false';
	var senderArray = acceptedOffer.senderProperties;
	var recipArray = acceptedOffer.recipientProperties;
	console.log(senderArray);
	console.log(recipArray);
	let senderProps = "";
	for(let i = 0; i < senderArray.length; i++) {
		if(i != senderArray.length-1) {
			senderProps += senderArray[i].name + ",";
		}
		else {
			senderProps += senderArray[i].name;
		}
	}
	let recipProps = "";
	for(let i = 0; i < recipArray.length; i++) {
		if(i != recipArray.length-1) {
			recipProps += recipArray[i].name + ",";
		}
		else {
			recipProps += recipArray[i].name;
		}
	}
	console.log(senderProps);
	console.log(recipProps);
	webSocket.send(JSON.stringify({
		'trade': 'trade',
		'accepted' : 'false',
		'rejected' : 'true',
		'sender' : acceptedOffer.sender,
		'recipient' : acceptedOffer.recipient,
		'senderProperties' : senderProps,
		'recipientProperties' : recipProps,
		'senderMoney' : receivedTrade.senderMoney,
		'recipientMoney' : receivedTrade.recipientMoney
	}))
}

function createPlayer() {
	
	webSocket.send(JSON.stringify({
		'message' : playerName.value
	}));
	$('.usernamePage').hide();
	$('.boardPage').show();
	

	
}
function send() {
	webSocket.send(JSON.stringify({
		'message' : messagetextField.value
	}));
	
	messagetextField.value="";
}

function buy() {
	webSocket.send(JSON.stringify({
		'action' : 'buy'
	}))

}

function roll(){
	webSocket.send(JSON.stringify({
		'action' : 'roll'
	}));
}

window.onbeforeunload = function (){
//	alert("Bye...");
	
// return 'You have unsaved changes!';
	webSocket.onclose = function () {};
	webSocket.close();
};
function rolldice(d1, d2 ){
    var diceImages=[
        "../images/dice1.png",
        "../images/dice2.png",
        "../images/dice3.png",
        "../images/dice4.png",
        "../images/dice5.png",
        "../images/dice6.png"
    ];
    var count = 0;
    var id = setInterval(frame, 100);
    function frame(){
        if(count == 15){
            var dice1 = d1 -1 ;
            //Math.floor((Math.random() * 6));
            var dice2 = d2 -1 ; 
            //Math.floor((Math.random() * 6));
            document.getElementById("dice1").src = diceImages[dice1];
            document.getElementById("dice2").src = diceImages[dice2];
            clearInterval(id);
            
        }
        else{
            var dice1 = Math.floor((Math.random() * 6));
            var dice2 = Math.floor((Math.random() * 6));
            document.getElementById("dice1").src = diceImages[dice1];
            document.getElementById("dice2").src = diceImages[dice2];
            count++;
        }
    }
    console.log('done with anumation');
}
function getDiceOne(){
    return 5;
}
function getDiceTwo(){
    return 5;
}

function  endTurn(){
	$('#buy-button').hide();
	webSocket.send(JSON.stringify({
		'action' : 'end'
	}))
}

//setInterval(function(){
//	document.getElementById('messagesTextArea').scrollTop = document.getElementById('messagesTextArea').scrollHeight;
//}, 1);

$(document).ready(function() {
$(".property").hover(function(){
	console.log("hovering");
	const board = gamestate.board;
	let spaceIndex = $(this).attr('id').replace( /^\D+/g, '');
    document.getElementById("space-card").innerHTML = "<div class='space-card-top' style='background-color: "+board[spaceIndex].color+";'><p>"+board[spaceIndex].name+"</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>RENT $"+board[spaceIndex].rent+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 1 House</p><p class='space-card-text'>"+"$"+board[spaceIndex].h1+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 2 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h2+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 3 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h3+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 4 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h4+".</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>With HOTEL $"+board[spaceIndex].hotel+".</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Mortgage Value $"+board[spaceIndex].mortgage+"</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Houses costs $"+board[spaceIndex].housingCost+", each.</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Hotels, $"+board[spaceIndex].housingCost+", plus 4 houses.</p></div>";
    $("#space-card").show();
},
function(){
    $("#space-card").hide();
})

//$(".railroad").hover(function(){
//	const board = gamestate.board;
//	var spaceIndex = $(this).attr('id').replace( /^\D+/g, '');
//    document.getElementById("space-card").innerHTML = "<div class='space-card-top' style='background-color: "+board[spaceIndex].color+";'><p>"+board[spaceIndex].name+"</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>RENT $"+board[spaceIndex].rent+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 1 House</p><p class='space-card-text'>"+"$"+board[spaceIndex].h1+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 2 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h2+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 3 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h3+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 4 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h4+".</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>With HOTEL $"+board[spaceIndex].hotel+".</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Mortgage Value $"+board[spaceIndex].mortgage+"</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Houses costs $"+board[spaceIndex].housingCost+", each.</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Hotels, $"+board[spaceIndex].housingCost+", plus 4 houses.</p></div>";
//    $("#space-card").show();
//},
//function(){
//    $("#space-card").hide();
//})

})