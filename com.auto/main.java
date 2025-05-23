import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

class GameData {
    private float averageSpeed;
    private int driftPoints;
    private float distanceTraveled;


    public float getAverageSpeed() { return averageSpeed; }
    public void setAverageSpeed(float averageSpeed) { this.averageSpeed = averageSpeed; }

    public int getDriftPoints() { return driftPoints; }
    public void setDriftPoints(int driftPoints) { this.driftPoints = driftPoints; }

    public float getDistanceTraveled() { return distanceTraveled; }
    public void setDistanceTraveled(float distanceTraveled) { this.distanceTraveled = distanceTraveled; }
}

public class DataServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/data", new DataHandler());
        server.start();
        System.out.println("Serwer działa na http://localhost:8080");
    }

    static class DataHandler implements HttpHandler {
        private final Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                try {
                    InputStream requestBody = exchange.getRequestBody();
                    String json = new String(requestBody.readAllBytes(), "UTF-8");

                    GameData data = gson.fromJson(json, GameData.class);
                    System.out.println("Odebrano dane: " + data.getAverageSpeed() + " km/h, " + data.getDriftPoints() + " punktów");

                    String response = "Dane odebrano!";
                    exchange.sendResponseHeaders(200, response.length());
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    exchange.sendResponseHeaders(500, 0);
                }
            } else {
                exchange.sendResponseHeaders(405, 0);
            }
            exchange.close();
        }
    }
}