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
	console.log(" before any conditions = ");
	var jsonData = JSON.parse(incomingMessage.data);
	console.log(jsonData);

	
	if (jsonData.messageType == "ChatMessage"){		
		messagesTextArea.value += jsonData.name+ ":  " + jsonData.message + '\n';		
	}
	
	else if(jsonData.messageType == "GamestateMessage") {
		gamestate = JSON.parse(jsonData.gamestate);	
		var playersList = gamestate.players;
		console.log("player list = "+ playersList);
		
		for (var i=0; i<playersList.length;i++){
			console.log("player = "+playersList[i].name + playersList[i].money);
			$('#player'+(i+1)).show();
			$('#playerbox'+(i+1)).show();
			document.getElementById("player"+(i+1)+"info").innerHTML = "<div class='col-4'>"+playersList[i].name+"</div><div class='col-4'>" + "$" + playersList[i].money +"</div>"; 
			document.getElementById("listproperties"+(i+1)).innerHTML="";
			for ( var j=0;j< playersList[i].ownedProperties.length;j++){
		
				var ul = document.getElementById("listproperties"+(i+1));
				var li = document.createElement("li");
				li.className = "list-group-item";
				  li.appendChild(document.createTextNode(playersList[i].ownedProperties[j].name));
				  ul.appendChild(li);
			}	
		}
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
				tradeOption.value = playersList[i].name;
				tradeOption.id = playersList[i].name;
				tradeWith.add(tradeOption);
			}

		}
		var giveProperties = document.getElementById("giveProperties");
		$("#giveProperties").empty();
		for(var i = 0; i < playersList[gamestate.currentPlayerIndex].ownedProperties.length; i++){
			var givePropertiesOption = document.createElement("option");
			givePropertiesOption.text = playersList[gamestate.currentPlayerIndex].ownedProperties[i].name;
			givePropertiesOption.value = playersList[gamestate.currentPlayerIndex].ownedProperties[i].name;
			givePropertiesOption.id = playersList[gamestate.currentPlayerIndex].name;
			giveProperties.add(givePropertiesOption);
		}
		
		var wantProperties = document.getElementById("wantProperties");
		$("#wantProperties").empty();
		$(document).ready(function(){
			$("#tradeWith").change(function(){
				console.log("Inside Jquery");
				var tradeWith = $(this).val();
				$("#wantProperties").empty();
				console.log(tradeWith);
				for(var i = 0; i <playersList.length; i++){
					if(tradeWith.valueOf() === playersList[i].name.valueOf()){
						for(var j = 0; j < playersList[i].ownedProperties.length; j++){
							var wantPropertiesOption = document.createElement("option");
							wantPropertiesOption.text = playersList[i].ownedProperties[j].name;
							wantPropertiesOption.value = playersList[i].ownedProperties[j].name;
							wantPropertiesOption.id = playersList[i].name;
							wantProperties.add(wantPropertiesOption);
						}
					}
				}
			});
		});
		
		document.getElementById("playerMoney").innerHTML = "You have $"+playersList[gamestate.currentPlayerIndex].money;

		document.getElementById('activity-log').innerHTML = '';
		for (var a=0; a<gamestate.activityLog.length;a++){
			var p = document.createElement("P");
			p.innerHTML = gamestate.activityLog[a];
			document.getElementById('activity-log').appendChild(p);
			
		}
		for (var i=0; i<playersList.length;i++){
			
			if (i==gamestate.currentPlayerIndex){
				
				if (playersList[i].doubleRolls == 0  && playersList[i].hasRolled == false ){
					$('#end-button').hide();
					$('#roll-button').show();
				}
				
				else if (gamestate.board[playersList[i].currentLocation].ownedBy === null  ){
					$('#buy-button').show();
				}
				else if (gamestate.board[playersList[i].currentLocation].ownedBy !== null  || gamestate.board[playersList[i].currentLocation].ownedBy === undefined ){
					$('#buy-button').hide();
				}
				if (playersList[i].hasRolled == true && playersList[i].doubleRolls == 0){
					$('#end-button').show();
					$('#roll-button').hide();
				}

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
		
		
	}
	
	else if (jsonData.messageType == "UserMessage") {
		usersTextArea.value = "";
		var i = 0;
		while (i < jsonData.user.length){			
			usersTextArea.value +=  jsonData.user[i] + "\n";
			i++;
		}
	}

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
	webSocket.send(JSON.stringify({
		'action' : 'end'
	}))
}

setInterval(function(){
	document.getElementById('messagesTextArea').scrollTop = document.getElementById('messagesTextArea').scrollHeight;
}, 1);


























