/**
 * 
 */
function myMove(player, index1, index2) {
            console.log(index1);
            console.log(index2);
            var elem = document.getElementById(player);
            var yLimit = elem.getBoundingClientRect().top - 720;
            var xLimit = elem.getBoundingClientRect().left - 720;
            var y = elem.getBoundingClientRect().top;
            var x = elem.getBoundingClientRect().left;
            var interval = setInterval(frame, 100);
            function frame(){
                if(index1 >= 0 && index1 < 10){
                    x -= 72;
                    elem.style.left = x + 'px';
                }
                if(index1 >= 10 && index1 < 20){
                    y -= 72;
                    elem.style.top = y + 'px';
                }
                if(index1 >= 20 && index1 < 30){
                    x += 72;
                    elem.style.left = x + 'px';
                }
                if(index1 >= 30 && index1 < 40){
                    y += 72;
                    elem.style.top = y + 'px';
                }
                index1++;
                index1 = (index1%40);

                if (index1 == index2){
                    clearInterval(interval);
                }
            }
        }


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
	
	else if (jsonData.messageType == "DiceMessage"){
		var dice = jsonData.dice1 + jsonData.dice2;
		messagesTextArea.value += jsonData.name+ ":  " + dice + '\n';
		rolldice(jsonData.dice1, jsonData.dice2);
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

function roll(){
	webSocket.send(JSON.stringify({
		'message' : 'roll'
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
            dice1Value.value=dice1+1;
            dice2Value.value=dice2+1;
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



