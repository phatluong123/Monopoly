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
		<textarea id="messagesTextArea" rows="5" cols="40"></textarea>
		<textarea id="usersTextArea" rows="5" cols="8"><c:out value="${newplayer.name }" /></textarea><br>
		<input id="messagetextField" type="text" size="20" /> <input
			type="button" value="Send Message" onClick="send()"> <br>
	</div>
</body>
</html>
