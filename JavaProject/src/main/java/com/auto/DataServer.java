package com.auto;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class DataServer {
    public static void main(String[] args) throws IOException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/data", new DataHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Serwer działa na http://localhost:8080");
    }

    static class DataHandler implements HttpHandler {
        private final Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    sendResponse(exchange, 405, "Metoda nieobsługiwana");
                    return;
                }

                InputStream requestBody = exchange.getRequestBody();
                String json = new String(requestBody.readAllBytes(), StandardCharsets.UTF_8);
                GameData data = gson.fromJson(json, GameData.class);

                System.out.println("Odebrano dane: " + data);

                sendResponse(exchange, 200, "Dane odebrano pomyślnie");
            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "Wystąpił błąd: " + e.getMessage());
            } finally {
                exchange.close();
            }
        }

        private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(statusCode, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }
}
