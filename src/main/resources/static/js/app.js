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
		console.log(gamestate);
	}
	
	else if (jsonData.messageType == "DiceMessage"){
		var dice = jsonData.dice1 + jsonData.dice2;
		messagesTextArea.value += jsonData.name+ ":  " + dice + '\n';
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



