<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dash board</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>

<script type="text/javascript" src="js/script.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script type="text/javascript" src="../js/app.js"></script>
<script type="text/javascript" src="../js/formSubmit.js"></script>
</head>
<body>
	<div id="boardPage" class="boardPage">
		<div class="row">
			<div class="corner"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="corner"></div>
		</div>
		<div class="horizontal-street">
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
		</div>
		<div class="empty-space">
			<form action="/roleDices" method="post" id="myForm">
				<div class='container text-center'>
					<div class='row'>
						<p class='mx-auto mt-3'>
							<button onclick="roll()" class='btn btn-info'>Roll Me!</button>
						</p>

						<div
							class='row mt-3 border border-dark mx-auto d-flex justify-content-around'
							style="height: 75px; width: 150px;">
							<div class='left-die my-auto' style="height: 50px; width: 50px;">
								<img id="dice1" src='../images/dice1.png'
									style="height: 50px; width: 50px;">
							</div>
							<div class='right-die my-auto' style="height: 50px; width: 50px;">
								<img id="dice2" src='../images/dice1.png'
									style="height: 50px; width: 50px;">
							</div>

						</div>
					</div>

				</div>
			</form>

		</div>
		<div class="horizontal-street">
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
			<div class="street-body"></div>
		</div>
		<div class="row">
			<div class="corner"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="street"></div>
			<div class="corner">
				<div class="player1" id="player1">
					<img src="../images/jerry.jpg">
				</div>
			</div>
		</div>
	</div>
	<div class="user-message">
		<h3 style="white-space : pre;">Messenger                       Players</h3>
		<textarea id='messagesTextArea' style="height:150px;width:300px; border-radius:10px;" ></textarea>
		<textarea id="usersTextArea" style="height:150px;width:100px; border-radius:10px;"><c:out value="${newplayer.name }" /></textarea>
		<br> <input id="messagetextField" type="text" size="30" list="emoji" style=" border-radius:10px;">
		<datalist id="emoji">
			<option value=&#x1F354>
			<option value=&#x1F600>
			<option value=&#x1F603>
			<option value=&#x1F604>
			<option value=&#x1F970>
			<option value=&#x1F4A9>
			<option value=&#x1F648>
			<option value=&#x1F4A3>
			<option value=&#x1F44B>
			<option value=&#x1F600>
			<option value=&#x1F601>
			<option value=&#x1F602>
			<option value=&#x1F603>
			<option value=&#x1F604>
			<option value=&#x1F605>
			<option value=&#x1F606>
			<option value=&#x1F607>
			<option value=&#x1F608>
			<option value=&#x1F609>
			<option value=&#x1F60A>
			<option value=&#x1F60B>
			<option value=&#x1F60C>
			<option value=&#x1F60D>
			<option value=&#x1F60E>
			<option value=&#x1F60F>
			<option value=&#x1F610>
			<option value=&#x1F611>
			<option value=&#x1F612>
			<option value=&#x1F613>
			<option value=&#x1F614>
			<option value=&#x1F615>
			<option value=&#x1F616>
			<option value=&#x1F617>
			<option value=&#x1F618>
			<option value=&#x1F619>
			<option value=&#x1F61A>
			<option value=&#x1F61B>
			<option value=&#x1F61C>
			<option value=&#x1F61D>
			<option value=&#x1F61E>
			<option value=&#x1F620>
			<option value=&#x1F621>
			<option value=&#x1F622>
			<option value=&#x1F623>
			<option value=&#x1F624>
			<option value=&#x1F625>
		</datalist>

		<input type="button" value="Send Message" onClick="send()"> <br>
	</div>
</body>
</html>
