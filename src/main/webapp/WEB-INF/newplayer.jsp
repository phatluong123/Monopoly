<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="../css/homepage.css">
<script type="text/javascript" src="../js/createPlayer.js"></script>
</head>
<body>
	<div class='container'>
			<h1 class='text-center mt-5'>Join Us!</h1>
		<div class='row'>
			<img class='mx-auto mt-5' src="../images/MonopolyMan.png">
		</div>
		<form action="/createplayer" method="post">
			<div class='form-group'>
				<div class='row justify-content-center'>
					<label class='my-auto col-1 text-right' for="playerName">Name </label>
					<input class='col-2 ml-2 form-control' id="playerName" name="playername" type="text"> 
				</div>
			</div>
			<div class='row'>
				<input class='btn btn-primary mx-auto px-5' type="submit" value="Play!">
			</div>
		</form>
	</div>


</body>
</html>
