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
    usernamePage.classList.add('hidden');
    chatPage.classList.remove('hidden');
}

window.onbeforeunload = function doSomething(){
	webSocket.onclose = function () {};
	webSocket.close();
};