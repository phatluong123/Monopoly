<!DOCTYPE html>
<html>
<head>
    <title>Roll The Dice!</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class='container text-center'>
        <h1 class='mt-5'>Roll The Dice!</h1>
        <div class='row'>
            <p class='mx-auto mt-3'><button onclick="roll()" class='btn btn-info'>Roll Me!</button></p>
        </div>
        <div class='row mt-3 border border-dark mx-auto d-flex justify-content-around' style="height: 300px; width: 650px;">
            <div class='left-die my-auto' style="height: 250px; width:250px;">
                <img id="dice1" src='dice1.png' style="height: 250px; width:250px;">
            </div>
            <div class='right-die my-auto' style="height: 250px; width:250px;">
                <img id="dice2" src='dice1.png' style="height: 250px; width:250px;">
            </div>
        </div>
    </div>
    <script>
    function roll(){
        var diceImages=[
            "dice1.png",
            "dice2.png",
            "dice3.png",
            "dice4.png",
            "dice5.png",
            "dice6.png"
        ];
        var count = 0;
        var id = setInterval(frame, 100);
        function frame(){
            if(count == 15){
                var dice1 = getDiceOne();
                var dice2 = getDiceTwo();
                document.getElementById("dice1").src = diceImages[dice1];
                document.getElementById("dice2").src = diceImages[dice2];
                clearInterval(id);
            }
            else{
                var dice1 = Math.floor((Math.random() * 6));
                var dice2 = Math.floor((Math.random() * 6));
                document.getElementById("dice1").src = diceImages[dice1];
                document.getElementById("dice2").src = diceImages[dice2];
                count++;
            }
        }
    }
    function getDiceOne(){
        return 5;
    }
    function getDiceTwo(){
        return 5;
    }
    </script>
</body>
</html>