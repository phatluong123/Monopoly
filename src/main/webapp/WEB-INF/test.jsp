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
 <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/app.js"></script>
<script type="text/javascript" src="../js/formSubmit.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="board">
			<div class="top-row">
				<div class="corner" id="space20"></div>
				<div class="top-space property" id="space21"></div>
				<div class="top-space" id="space22"></div>
				<div class="top-space property" id="space23"></div>
				<div class="top-space property" id="space24"></div>
				<div class="top-space railroad" id="space25"></div>
				<div class="top-space property" id="space26"></div>
				<div class="top-space property" id="space27"></div>
				<div class="top-space utility" id="space28"></div>
				<div class="top-space property" id="space29"></div>
				<div class="corner" id="space30"></div>
			</div>
			<div class="mid-row">
				<div class="left-col">
					<div class="side-space property" id="space19"></div>
					<div class="side-space property" id="space18"></div>
					<div class="side-space" id="space17"></div>
					<div class="side-space property" id="space16"></div>
					<div class="side-space railroad" id="space15"></div>
					<div class="side-space property" id="space14"></div>
					<div class="side-space property" id="space13"></div>
					<div class="side-space utility" id="space12"></div>
					<div class="side-space property" id="space11"></div>
				</div>
				<div class="mid-col">
						<div class="row dice-bar mt-2">
							<div class="my-auto ml-3"><button onclick="endTurn()" class='btn btn-info ' id="end-button">End Turn!</button></div>
							<div class="my-auto ml-3"><button onclick="buy()" class='btn btn-info ' id="buy-button">Buy!</button></div>
							<div class="my-auto ml-3"><button onclick="useCard()" class='btn btn-info ' id="useJailCardButton">Use Card</button></div>
							<div class="my-auto ml-3"><button onclick="pay()" class='btn btn-info ' id="payFineButton">Pay $50</button></div>
							<div class='ml-auto mr-2 mt-3 d-inline-block my-auto'>
								<button onclick="roll()" class='btn btn-info' id="roll-button">Roll!</button>
							</div>	
							<div class='left-die my-auto mr-2'>
								<img id="dice1" src='../images/dice1.png'>
							</div>
							<div class='right-die my-auto mr-2'>
								<img id="dice2" src='../images/dice1.png'>
							</div>
						</div>
						<div>
						
						</div>
					<div id="activity-log"  class="activity-log mx-auto mt-2" ></div>
					<div class='row justify-content-center'>
						<button class='btn btn-primary mt-2' data-toggle='modal' data-target="#trade" type="submit" id="trade-button">Trade</button>
						<button class='btn btn-primary mt-2 ml-5' data-toggle='modal' data-target="#housing" type="submit" id="build-button">Build</button>
					</div>
					<div id="space-card">
					</div>
					<div id="trade" class="modal fade" tabindex="-1">
				        <div class="modal-dialog">
				            <div class="modal-content">
				                <div class="modal-header text-center">
					            	<h5 class="modal-title mx-auto pl-3">Trade Window </h5>
					            	<button type="button" class="close ml-0 pl-0" data-dismiss="modal">&times;</button>
				                </div>
				                <div class="modal-body">
				                	<p class='text-center'>Who do you want to trade with?</p>
				                		<select class='form-control col-lg-6 mx-auto' name='tradeWith' id='tradeWith'>
				                			<option value="">--Select--</option>
					                    </select>
					                <hr>
				                    <p class='text-center'>What do you want?</p>
				                    	<div class='row col-lg-6 mx-auto'>
					                    	<select multiple class='form-control' name="requestProperties" id="requestProperties">
					                    	</select>
				                    	</div>
				                    	<div class='row mt-3 d-flex justify-content-center'>
				            				<p class='my-auto'>Request </p>
				                    		<div class='ml-2'>$<input class='col-lg-10 ml-1' type="number" class='form-control' name="moneyRequest" id="moneyRequest" value="0" min="0"></div>
				                    	</div>
				                    	<hr>
				                    	<p class='mt-3 text-center'>What will you give?</p>
				                    	<div class='row col-lg-6 mx-auto'>
					                    	<select multiple class='form-control' name="offerProperties" id="offerProperties">
					                    	</select>
				                    	</div>
				                    	<div class='row mt-3 d-flex justify-content-around'>
				                    		<div class='mx-auto'>
				                    			$<input class='col-lg-10 ml-1' type="number" class='form-control' name="moneyOffer" id="moneyOffer" value="0" min="0">
				                    		</div>
				                    	</div>
				                    	<p class='mt-2 text-center' id="playerMoney"></p>
				                    	<div class='row'>
				                    		<button class='btn btn-primary mt-3 mx-auto' type="submit" data-dismiss="modal" onClick="sendTradeOffer()">Offer</button>
				                    	</div>
				                </div>
				            </div>
				        </div>
				    </div>
				    
					<div id="housing" class="modal fade" tabindex="-1">
				        <div class="modal-dialog">
				            <div class="modal-content">
				                <div class="modal-header text-center">
					            	<h5 class="modal-title mx-auto pl-3">Housing</h5>
					            	<button type="button" class="close ml-0 pl-0" data-dismiss="modal">&times;</button>
				                </div>
				                <div class="modal-body">
				                	<p class='text-center'>Which set to build on?</p>
				                		<select class='form-control col-lg-4 mx-auto' name='buildSet' id='buildSet'>
				                			<option value="">---Select---</option>
				                			<option value="tomato">Tomato</option>
				                			<option value="royal blue">Royal Blue</option>
					                    </select>
					            	<div id="buildingBody">
										<table class='table table-borderless text-center mt-3'>
											<thead>
												<tr>
													<th class='border-bottom border-dark'>Properties</th>
													<th class='border-bottom border-dark'>#</th>
													<th class='border-bottom border-dark'>Buy More</th>
												</tr>
											</thead>
											<tbody id='building'>
												<tr>
													<td>Kentucky Ave.</td>
													<td>2</td>
													<td><button class='btn btn-primary btn-sm' type="submit">Build</button>
												</tr>	
											</tbody>
										</table>
				                    </div>
				                </div>
				            </div>
				        </div>
				    </div>
				    <div id="trade-zone"  > 

				    </div>
				    
				    
					</div>

				<div class="right-col">
					<div class="side-space property" id="space31"></div>
					<div class="side-space property" id="space32"></div>
					<div class="side-space" id="space33"></div>
					<div class="side-space property" id="space34"></div>
					<div class="side-space railroad" id="space35"></div>
					<div class="side-space" id="space36"></div>
					<div class="side-space property" id="space37"></div>
					<div class="side-space" id="space38"></div>
					<div class="side-space property" id="space39"></div>
				</div>
			</div>
			<div class="bot-row">
				<div class="top-row">
					<div class="corner" id="space10"></div>
					<div class="top-space property" id="space9"></div>
					<div class="top-space property" id="space8"></div>
					<div class="top-space" id="space7"></div>
					<div class="top-space property" id="space6"></div>
					<div class="top-space railroad" id="space5"></div>
					<div class="top-space" id="space4"></div>
					<div class="top-space property" id="space3"></div>
					<div class="top-space" id="space2"></div>
					<div class="top-space property" id="space1"></div>
					<div class="corner" id="space0">
						<div id="player1"></div>
						<div id="player2"></div>
						<div id="player3"></div>
						<div id="player4"></div>		
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
				<div id="playerbox1" class="card mt-2" data-toggle="collapse" data-target="#playerbox1toggle">
					<div class="playerInfo">
						<div id="player1info" class="row justify-content-center font-14"></div>
					</div>	
					<div id="playerbox1toggle" class='collapse hide' data-parent="#accordion">
						<div class='card-body'>
							<ul id="listproperties1" class='list-group'>
								
							</ul>
						</div>
					</div>
				</div>
				<div id="playerbox2" class="card mt-2" data-toggle="collapse" data-target="#playerbox2toggle">
					<div class="playerInfo">
						<div id="player2info" class="row justify-content-center font-14"></div>
					</div>	
					<div id="playerbox2toggle" class='collapse hide' data-parent="#accordion">
						<div class='card-body'>
							<ul id="listproperties2" class='list-group'></ul>
						</div>
					</div>
				</div>
				<div id="playerbox3" class="card mt-2" data-toggle="collapse" data-target="#playerbox3toggle">
					<div class="playerInfo">
						<div id="player3info" class="row justify-content-center font-14"></div>
					</div>	
					<div id="playerbox3toggle" class='collapse hide' data-parent="#accordion">
						<div class='card-body'>
							<ul id="listproperties3" class='list-group'></ul>
						</div>
					</div>
				</div>
				<div id="playerbox4" class="card mt-2" data-toggle="collapse" data-target="#playerbox4toggle">
					<div class="playerInfo">
						<div id="player4info" class="row justify-content-center font-14"></div>
					</div>	
					<div id="playerbox4toggle" class='collapse hide' data-parent="#accordion">
						<div class='card-body'>
							<ul id="listproperties4" class='list-group'></ul>
						</div>
					</div>
				</div>		
			</div>
		</div>
	<div class="row mt-3">
		<button class="btn btn-primary ml-auto mr-3" onClick="reset()">Reset Game</button>
	</div>
	<div class="row mt-3 justify-content-end">
		<div id="resetWindow" class="col-10 mr-3">
			<div class="row justify-content-center">
				<textarea class="col" id="resetMessage">Do you want to reset the game?</textarea>
			</div>
			<div class="row justify-content-center mt-3" id="button-div">
				<button class="btn btn-primary ml-auto mr-3" onClick="acceptResest()">Accept Reset Game</button>
				<button class="btn btn-danger ml-auto" onClick="rejectReset()">Reject Reset Game</button>
			</div>
		</div>
	</div>
	</div>
	
</div>
</body>
</html>