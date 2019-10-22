/**
 * 
 */
var webSocket = new WebSocket('ws://54.153.37.22:8080/chatServerEndPoint');

webSocket.onmessage = function processMessage(incomingMessage) {
	var jsonData = JSON.parse(incomingMessage.data);
	console.log(jsonData);
	console.log(jsonData.name);
	if (jsonData.messageType == "ChatMessage")
		messagesTextArea.value += jsonData.name + '(' + jsonData.location
				+ '): ' + jsonData.message + '\n';
	else if (jsonData.messageType == "UserMessage") {
		usersTextArea.value = "";
		var i = 0;
		while (i < jsonData.user.length)
			usersTextArea.value += jsonData.user[i++] + "\n";
	}

}


function send() {
	webSocket.send(JSON.stringify({
		'message' : playerName.value,
		'location' : "default"
	}));
}



window.onbeforeunload = function doSomething(){
	webSocket.onclose = function () {};
	webSocket.close();
};