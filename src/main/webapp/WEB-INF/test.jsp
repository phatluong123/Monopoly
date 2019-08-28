<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dash board</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
	<div class="wrapper">
		<div class="board">
			<div class="top-row">
				<div class="corner" id="space20"></div>
				<div class="top-space" id="space21"></div>
				<div class="top-space" id="space22"></div>
				<div class="top-space" id="space23"></div>
				<div class="top-space" id="space24"></div>
				<div class="top-space" id="space25"></div>
				<div class="top-space" id="space26"></div>
				<div class="top-space" id="space27"></div>
				<div class="top-space" id="space28"></div>
				<div class="top-space" id="space29"></div>
				<div class="corner" id="space30"></div>
			</div>
			<div class="mid-row">
				<div class="left-col">
					<div class="side-space" id="space19"></div>
					<div class="side-space" id="space18"></div>
					<div class="side-space" id="space17"></div>
					<div class="side-space" id="space16"></div>
					<div class="side-space" id="space15"></div>
					<div class="side-space" id="space14"></div>
					<div class="side-space" id="space13"></div>
					<div class="side-space" id="space12"></div>
					<div class="side-space" id="space11"></div>
				</div>
				<div class="mid-col">
						<div class="row dice-bar">
							<p class="my-auto ml-3"><button onclick="endTurn()" class='btn btn-info ' >End Turn!</button></p>
							<p class="my-auto ml-3" style="display:hidden" ><button onclick="buy()" class='btn btn-info ' >Buy!</button></p>
							<p class='ml-auto mr-2 mt-3 d-inline-block '>
								
								<button onclick="roll()" class='btn btn-info' >Roll!</button>
								
				
							</p>	
							<div class='left-die my-auto mr-2' style="height: 50px; width: 50px;">
								<img id="dice1" src='../images/dice1.png' style="height: 50px; width: 50px;">
							</div>
							<div class='right-die my-auto mr-2' style="height: 50px; width: 50px;">
								<img id="dice2" src='../images/dice1.png'style="height: 50px; width: 50px;">
							</div>
						</div>
						<div>
						
						</div>
					</div>
				<div class="right-col">
					<div class="side-space" id="space31"></div>
					<div class="side-space" id="space32"></div>
					<div class="side-space" id="space33"></div>
					<div class="side-space" id="space34"></div>
					<div class="side-space" id="space35"></div>
					<div class="side-space" id="space36"></div>
					<div class="side-space" id="space37"></div>
					<div class="side-space" id="space38"></div>
					<div class="side-space" id="space39"></div>
				</div>
			</div>
			<div class="bot-row">
				<div class="top-row">
					<div class="corner" id="space10"></div>
					<div class="top-space" id="space9"></div>
					<div class="top-space" id="space8"></div>
					<div class="top-space" id="space7"></div>
					<div class="top-space" id="space6"></div>
					<div class="top-space" id="space5"></div>
					<div class="top-space" id="space4"></div>
					<div class="top-space" id="space3"></div>
					<div class="top-space" id="space2"></div>
					<div class="top-space" id="space1"></div>
					<div class="corner" id="space0">
						<div id="player1"
							style="display:none; height: 20px; width: 20px; background-color: pink;">
						</div>
						<div id="player2"
							style="display:none; height: 20px; width: 20px; background-color: green;">
						</div>
						<div id="player3"
							style="display:none; height: 20px; width: 20px; background-color: blue;">
						</div>
						<div id="player4"
							style="display:none; height: 20px; width: 20px; background-color: grey;">
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<div class="player-panel">
			<div class="user-message">
				<h3>Messenger     Players</h3>
				<textarea id='messagesTextArea'></textarea>
				<textarea id="usersTextArea"><c:out value="${newplayer.name }" /></textarea>
				<br> <input id="messagetextField" type="text" list="emoji">
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

				<input type="button" value="Send Message" onClick="send()">

			<div id="accordion">
				<div id="playerbox1" class="card " style="display:none; background-color:#e9ede8;">
					<div class='card-header'>
						<h5>
						<p id="player1name"><p>
						<button class='btn btn-primary btn-sm w-100' data-toggle="collapse" data-target="#playerbox1toggle">List of Properties</button>
						</h5>
					</div>	
					<div id="playerbox1toggle" class='collapse hide' data-parent="#accordion">
						<div class='card-body'>
							<ul id="listproperties" class='list-group'>
								<li ></li>
							</ul>
						</div>
					</div>
				</div>
				<div id="playerbox2" class="card" style="display:none; background-color:#e9ede8;">
					<div class='card-header'>
						<h5>
						<p id="player2name"><p>
						<button class='btn btn-primary btn-sm w-100' data-toggle="collapse" data-target="#playerbox2toggle">List of Properties</button>
						</h5>
					</div>	
					<div id="playerbox2toggle" class='collapse hide' data-parent="#accordion">
						<div class='card-body'>
							Test Properties
						</div>
					</div>
				</div>
				<div id="playerbox3" class="card" style="display:none; background-color:#e9ede8;">
					<div class='card-header'>
						<h5>
						<p id="player3name"><p>
						<button class='btn btn-primary btn-sm w-100' data-toggle="collapse" data-target="#playerbox3toggle">List of Properties</button>
						</h5>
					</div>	
					<div id="playerbox3toggle" class='collapse hide' data-parent="#accordion">
						<div class='card-body'>
							Test Properties
						</div>
					</div>
				</div>
				<div id="playerbox4" class="card" style="display:none; background-color:#e9ede8;">
					<div class='card-header'>
						<h5>
						<p id="player4name"><p>
						<button class='btn btn-primary btn-sm w-100' data-toggle="collapse" data-target="#playerbox4toggle">List of Properties</button>
						</h5>
					</div>	
					<div id="playerbox4toggle" class='collapse hide' data-parent="#accordion">
						<div class='card-body'>
							Test Properties
						</div>
					</div>
				</div>		
			</div>
		</div>
	</div>
	</div>
</body>
</html>
