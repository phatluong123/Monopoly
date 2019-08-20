/**
 * 
 */
function myMove() {
	var elem = document.getElementById("player1");
	var pos = 0;
	var pos_up=0;
	var distance=-700;
	var up = -700;
	var id = setInterval(frame, .5);
	function frame() {
		if (pos == distance) {
			clearInterval(id);
		} else {
			pos--;
			elem.style.left = pos + 'px';

		}

		
	}

}