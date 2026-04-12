import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

@ServerEndpoint("/game")
public class RPSServer {

    static Map<Integer, Session> players = new HashMap<>();
    static Map<Integer, String> moves = new HashMap<>();
    static boolean gameStarted = false;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        JSONObject msg = new JSONObject();
        msg.put("type", "status");
        msg.put("message", "Connected to server. Waiting for opponent...");
        session.getBasicRemote().sendText(msg.toJSONString());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        try {
            JSONObject data = new JSONObject(message);
            Integer playerId = ((Long) data.get("player")).intValue();
            String move = (String) data.get("move");

            players.put(playerId, session);
            moves.put(playerId, move);

            if (players.containsKey(1) && players.containsKey(2) && !gameStarted) {
                JSONObject start = new JSONObject();
                start.put("type", "gameStart");
                players.get(1).getBasicRemote().sendText(start.toJSONString());
                players.get(2).getBasicRemote().sendText(start.toJSONString());
                gameStarted = true;
            }

            if (moves.containsKey(1) && moves.containsKey(2)) {
                String move1 = moves.get(1);
                String move2 = moves.get(2);
                String winner = getWinner(move1, move2);

                JSONObject response1 = new JSONObject();
                response1.put("type", "result");
                response1.put("yourMove", move1);
                response1.put("opponentMove", move2);
                response1.put("result", winner.equals("Player1 Wins") ? "You Win!" : winner.equals("Draw") ? "It's a tie" : "You Lose");
                players.get(1).getBasicRemote().sendText(response1.toJSONString());

                JSONObject response2 = new JSONObject();
                response2.put("type", "result");
                response2.put("yourMove", move2);
                response2.put("opponentMove", move1);
                response2.put("result", winner.equals("Player2 Wins") ? "You Win!" : winner.equals("Draw") ? "It's a tie" : "You Lose");
                players.get(2).getBasicRemote().sendText(response2.toJSONString());

                moves.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        Integer removeKey = null;
        for (Map.Entry<Integer, Session> entry : players.entrySet()) {
            if (entry.getValue().equals(session)) {
                removeKey = entry.getKey();
                break;
            }
        }
        if (removeKey != null) {
            players.remove(removeKey);
            moves.remove(removeKey);
        }
        gameStarted = false;
    }

    private String getWinner(String p1, String p2) {
        if (p1.equals(p2)) return "Draw";
        if ((p1.equals("rock") && p2.equals("scissors")) ||
            (p1.equals("paper") && p2.equals("rock")) ||
            (p1.equals("scissors") && p2.equals("paper")))
            return "Player1 Wins";
        return "Player2 Wins";
    }
}