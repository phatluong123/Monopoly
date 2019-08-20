

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dash board</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/app.js"></script>
</head>
<body>
	<div class="board">
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
			<button onclick="myMove()">Click Me</button>
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
</body>
</html>
