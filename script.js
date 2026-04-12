let socket = new WebSocket("ws://localhost:8080/game");

socket.onopen = () => {
    document.getElementById("status").innerText = "Connected! Waiting for opponent...";
};

socket.onmessage = (event) => {
    const data = JSON.parse(event.data);
    
    if (data.type === "gameStart") {
        document.getElementById("status").innerText = "Game started! Make your move.";
    } else if (data.type === "result") {
        let msg = `You: ${data.yourMove} | Opponent: ${data.opponentMove} | ${data.result}`;
        document.getElementById("result").innerText = msg;
        document.getElementById("status").innerText = "Ready for next round";
    }
};

function sendMove(move) {
    socket.send(JSON.stringify({move: move}));
    document.getElementById("status").innerText = `You played ${move}. Waiting...`;
}