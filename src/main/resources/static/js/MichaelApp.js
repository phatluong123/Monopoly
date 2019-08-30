$(document).ready(function() {
$(".property").hover(function(){
	console.log("hovering");
	const board = gamestate.board;
	let spaceIndex = $(this).attr('id').replace( /^\D+/g, '');
    document.getElementById("space-card").innerHTML = "<div class='space-card-top' style='background-color: "+board[spaceIndex].color+";'><p>"+board[spaceIndex].name+"</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>RENT $"+board[spaceIndex].rent+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 1 House</p><p class='space-card-text'>"+"$"+board[spaceIndex].h1+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 2 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h2+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 3 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h3+".</p></div><div class='row justify-content-around space-card-row'><p class='space-card-text'>With 4 Houses</p><p class='space-card-text'>"+"$"+board[spaceIndex].h4+".</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>With HOTEL $"+board[spaceIndex].hotel+".</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Mortgage Value $"+board[spaceIndex].mortgage+"</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Houses costs $"+board[spaceIndex].structureCost+", each.</p></div><div class='row justify-content-center space-card-row'><p class='space-card-text'>Hotels, $"+board[spaceIndex].structureCost+", plus 4 houses.</p></div>";
    $("#space-card").show();
},
function(){
    $("#space-card").hide();
})