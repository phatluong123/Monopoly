<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="THIS IS A 'PLAYGROUND BOARD' TO TEST DIFFERENT VARIANTS OF THE BOARD LAYOUT.">
<title>TEST BOARD</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<style>

* {
	margin: 0;
	padding: 0;
	line-height: 0px;
    border: 1px solid pink;
}

/* PHAT'S ORIGINAL SETTINGS HAD THE BOARD DIMENSIONS AT 856 to 856 */


.board {
	width: 770px;
	height: 770px;
	background-image: url("C:/Users/wadea/Desktop/Coding_Dojo/Java_stack/Project/Monopoly/src/main/resources/static/images/board.jpg");
	background-size: 100%;
	background-repeat: no-repeat;
	background-color: green;
	display: inline-block;
}

.row {
	margin: 0;
	padding: 0;
	height: 50px;
    width: 770px;
	vertical-align: top;
    box-sizing: border-box;
}

.street {
	width: 70px;
	display: inline-block;
	border: 1px solid red;
	vertical-align: top;
}

.corner {
	width: 113px;
	border: 1px solid red;
	display: inline-block;
	vertical-align: top;
}

.horizontal-street {
	margin: 0;
	padding: 0;
	display: inline-block;
}

.street-body {
	vertical-align: top;
	width: 113px;
	border: 1px solid green;
	height: 70px;
	vertical-align: top;
}

.empty-space {
	width: 621px;
	height: 630px;
	border: 1px solid blue;
	display: inline-block;
	vertical-align: top;
}

.player1 img {
	width: 30%;

}

.player1 {
	position: absolute;
    height: 15px;
    width: 15px;
    /* ORIGINAL TOP AND LEFT PLACEMENT IS BELOW */
	/* top: 775px;
	left: 790px; */
    background-color: blue;
}
.player2 {
	position: absolute;
    height: 15px;
    width: 15px;
    /* ORIGINAL TOP AND LEFT PLACEMENT IS BELOW */
	/* top: 775px;
	left: 765px; */
    background-color: red;
}
.player3 {
	position: absolute;
    height: 15px;
    width: 15px;
    /* ORIGINAL TOP AND LEFT PLACEMENT IS BELOW */
	/* top: 800px;
	left: 790px; */
    background-color: green;
}
.player4 {
	position: absolute;
    height: 15px;
    width: 15px;
    /* ORIGINAL TOP AND LEFT PLACEMENT IS BELOW */
	/* top: 800px;
	left: 765px; */
    background-color: yellow;
}
.player_panel{
    display: inline-block;
    height: 770px;
    width: 200px;
}

</style>
<body onclick="showCoords(event)">
    <div>
        <div class="board">
            <div class="player1" id="player1"></div>
            <div class="player2" id="player2"></div>
            <div class="player3" id="player3"></div>
            <div class="player4" id="player4"></div>
        </div>
        <div class="player_panel"></div>
    </div>
    <script>
        function myMove(player, index1, index2) {
            console.log(index1);
            console.log(index2);
            var elem = document.getElementById(player);
            var yLimit = elem.getBoundingClientRect().top - 720;
            var xLimit = elem.getBoundingClientRect().left - 720;
            var y = elem.getBoundingClientRect().top;
            var x = elem.getBoundingClientRect().left;
            var interval = setInterval(frame, 250);
            function frame(){
                if(index1 >= 0 && index1 < 10){
                    x -= 71;
                    elem.style.left = x + 'px';
                }
                if(index1 >= 10 && index1 < 20){
                    y -= 72;
                    elem.style.top = y + 'px';
                }
                if(index1 >= 20 && index1 < 30){
                    x += 71;
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
                if (player == "player1" && index1 == 10){
                    elem.style.top = 750 + 'px';
                    elem.style.left = 5 + 'px';
                }
                if (player == "player2" && index1 == 10){
                    elem.style.top = 770 + 'px';
                    elem.style.left = 5 + 'px';
                }
                if (player == "player3" && index1 == 10){
                    elem.style.top = 790 + 'px';
                    elem.style.left = 5 + 'px';
                }
                if (player == "player4" && index1 == 10){
                    elem.style.top = 810 + 'px';
                    elem.style.left = 5 + 'px';
                }
                if (player == "player1" && index1 == 11){
                    elem.style.top = 685 + 'px';
                    elem.style.left = 65 + 'px';
                }
                if (player == "player2" && index1 == 11){
                    elem.style.top = 685 + 'px';
                    elem.style.left = 40 + 'px';
                }
                if (player == "player3" && index1 == 11){
                    elem.style.top = 710 + 'px';
                    elem.style.left = 65 + 'px';
                }
                if (player == "player4" && index1 == 11){
                    elem.style.top = 710 + 'px';
                    elem.style.left = 40 + 'px';
                }
                if (player == "player1" && index1 == 20){
                    elem.style.top = 40 + 'px';
                    elem.style.left = 75 + 'px';
                }
                if (player == "player2" && index1 == 20){
                    elem.style.top = 40 + 'px';
                    elem.style.left = 50 + 'px';
                }
                if (player == "player3" && index1 == 20){
                    elem.style.top = 65 + 'px';
                    elem.style.left = 75 + 'px';
                }
                if (player == "player4" && index1 == 20){
                    elem.style.top = 65 + 'px';
                    elem.style.left = 50 + 'px';
                }
                if (player == "player1" && index1 == 30){
                    elem.style.top = 40 + 'px';
                    elem.style.left = 810 + 'px';
                }
                if (player == "player2" && index1 == 30){
                    elem.style.top = 40 + 'px';
                    elem.style.left = 785 + 'px';
                }
                if (player == "player3" && index1 == 30){
                    elem.style.top = 65 + 'px';
                    elem.style.left = 810 + 'px';
                }
                if (player == "player4" && index1 == 30){
                    elem.style.top = 65 + 'px';
                    elem.style.left = 785 + 'px';
                }
            }
        }
        
        function showCoords(event) {
            var sX = event.clientX;
            var sY = event.clientY;
            var coords2 = "screen - X: " + sX + ", Y coords: " + sY;
            console.log(coords2);
        }
        
        function displayPlayer1(){
            
        }

    </script>
</body>
</html>